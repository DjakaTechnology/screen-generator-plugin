package data.file

import data.repository.SettingsRepository
import data.repository.SourceRootRepository
import model.Category
import model.FileType
import model.Settings

private const val LAYOUT_DIRECTORY = "layout"

interface FileCreator {
    fun createScreenFiles(category: Category, packageName: String, screenName: String, module: String)
}

class FileCreatorImpl(
        private val settingsRepository: SettingsRepository,
        private val sourceRootRepository: SourceRootRepository
) : FileCreator {

    override fun createScreenFiles(
            category: Category,
            packageName: String,
            screenName: String,
            module: String
    ) {
        settingsRepository.loadSettings().apply {
            category.screenElements.forEach {
                if (it.fileType == FileType.LAYOUT_XML) {
                    val resourcesSubdirectory = findResourcesSubdirectory(module)
                    val file = File(
                            it.fileName(screenName, packageName),
                            it.body(screenName, packageName),
                            it.fileType
                    )
                    resourcesSubdirectory.addFile(file)
                } else {
                    val codeSubdirectory = (if (it.fileType == FileType.KOTLIN) findCodeSubdirectory(packageName, module)
                    else findTestSubdirectory(packageName, module)) ?: return

                    val body = it.body(screenName, packageName)
                    val packageLine = body.lines()[0]
                    val packageNameFromTemplate = packageLine.split(" ")[1]
                    if (packageNameFromTemplate == packageName) {
                        val file = File(
                                it.fileName(screenName, packageName),
                                it.body(screenName, packageName),
                                it.fileType
                        )
                        codeSubdirectory.addFile(file)
                    } else {
                        val innerCodeSubDirectory = findCodeSubdirectory(packageNameFromTemplate, module)
                        innerCodeSubDirectory?.let { directory ->
                            val file = File(
                                    it.fileName(screenName, packageName),
                                    it.body(screenName, packageName),
                                    it.fileType
                            )
                            directory.addFile(file)
                        } ?: kotlin.run {
                            val file = File(
                                    it.fileName(screenName, packageName),
                                    it.body(screenName, packageName),
                                    it.fileType
                            )
                            codeSubdirectory.addFile(file)
                        }
                    }
                }
            }
        }
    }

    private fun findCodeSubdirectory(packageName: String, module: String): Directory? =
            sourceRootRepository.findCodeSourceRoot(module)?.run {
                return findOrCreateDirectory(directory, packageName)
            }

    private fun findTestSubdirectory(packageName: String, module: String): Directory? =
            sourceRootRepository.findTestCodeSourceRoot(module)?.run {
                return findOrCreateDirectory(directory, packageName)
            }

    private fun findOrCreateDirectory(directory: Directory, packageName: String): Directory {
        var subdirectory = directory
        packageName.split(".").forEach {
            subdirectory = subdirectory.findSubdirectory(it) ?: subdirectory.createSubdirectory(it)
        }
        return subdirectory
    }

    private fun findResourcesSubdirectory(module: String) =
            sourceRootRepository.findResourcesSourceRoot(module).directory.run {
                findSubdirectory(LAYOUT_DIRECTORY) ?: createSubdirectory(LAYOUT_DIRECTORY)
            }
}