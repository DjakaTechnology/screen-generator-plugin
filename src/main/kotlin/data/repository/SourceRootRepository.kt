package data.repository

import data.file.ProjectStructure
import data.file.SourceRoot

interface SourceRootRepository {

    fun findCodeSourceRoot(module: String): SourceRoot?
    fun findResourcesSourceRoot(module: String): SourceRoot
    fun findTestCodeSourceRoot(module: String): SourceRoot?
}

class SourceRootRepositoryImpl(private val projectStructure: ProjectStructure) : SourceRootRepository {
    override fun findTestCodeSourceRoot(module: String) =
            projectStructure.findSourceRoots(module).firstOrNull {
                val pathTrimmed = it.path.removeModulePathPrefix(module)
                pathTrimmed.contains("src")
                        && pathTrimmed.contains("test")
                        && !pathTrimmed.contains("androidTest")
                        && !pathTrimmed.contains("res")
            }

    override fun findCodeSourceRoot(module: String) =
            projectStructure.findSourceRoots(module).firstOrNull {
                val pathTrimmed = it.path.removeModulePathPrefix(module)
                pathTrimmed.contains("src")
                        && pathTrimmed.contains("main")
                        && !pathTrimmed.contains("assets")
                        && !pathTrimmed.contains("test")
                        && !pathTrimmed.contains("res")
                        && !pathTrimmed.contains("androidTest")
            }

    override fun findResourcesSourceRoot(module: String) =
            projectStructure.findSourceRoots(module).first {
                val pathTrimmed = it.path.removeModulePathPrefix(module)
                pathTrimmed.contains("src")
                        && pathTrimmed.contains("main")
                        && pathTrimmed.contains("res")
            }

    private fun String.removeModulePathPrefix(module: String) =
            removePrefix(projectStructure.getProjectPath() + "/" + module)
}