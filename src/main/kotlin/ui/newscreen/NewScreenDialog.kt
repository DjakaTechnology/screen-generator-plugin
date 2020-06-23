package ui.newscreen

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SettingsRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import model.Category
import javax.swing.JComponent

class NewScreenDialog(project: Project, currentPath: CurrentPath?) : DialogWrapper(true), NewScreenView {

    private val panel = NewScreenPanel()

    private val presenter: NewScreenPresenter

    init {
        val projectStructure = ProjectStructureImpl(project)
        val sourceRootRepository = SourceRootRepositoryImpl(projectStructure)
        val settingsImpl = SettingsRepositoryImpl(project)
        val fileCreator = FileCreatorImpl(SettingsRepositoryImpl(project), sourceRootRepository)
        val packageExtractor = PackageExtractorImpl(currentPath, sourceRootRepository)
        val writeActionDispatcher = WriteActionDispatcherImpl(project)
        val moduleRepository = ModuleRepositoryImpl(projectStructure)
        presenter = NewScreenPresenter(this, settingsImpl, fileCreator, packageExtractor, writeActionDispatcher, moduleRepository, currentPath)
        init()
    }

    override fun doOKAction() =
            presenter.onOkClick(
                    panel.categoryComboBox.selectedItem as Category,
                    panel.packageTextField.text,
                    panel.nameTextField.text,
                    panel.moduleComboBox.selectedItem as String
            )

    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panel
    }

    override fun close() = close(DialogWrapper.OK_EXIT_CODE)

    override fun showPackage(packageName: String) {
        panel.packageTextField.text = packageName
    }

    override fun showCategories(categories: List<Category>) = categories.forEach { panel.categoryComboBox.addItem(it) }

    override fun selectCategory(category: Category) {
        panel.categoryComboBox.selectedItem = category
    }

    override fun showModules(modules: List<String>) = modules.forEach { panel.moduleComboBox.addItem(it) }

    override fun selectModule(module: String) {
        panel.moduleComboBox.selectedItem = module
    }
}