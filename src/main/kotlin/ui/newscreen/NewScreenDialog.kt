package ui.newscreen

import com.intellij.openapi.ui.DialogWrapper
import data.file.CurrentPath
import di.Injector
import model.AndroidComponent
import javax.inject.Inject
import javax.swing.JComponent

class NewScreenDialog(currentPath: CurrentPath?) : DialogWrapper(true), NewScreenView {

    private val panel = NewScreenPanel()

    @Inject
    lateinit var presenter: NewScreenPresenter

    init {
        Injector.component.newScreenFactory().create(this, currentPath).inject(this)
        init()
    }

    override fun doOKAction() =
        presenter.onOkClick(
            panel.packageTextField.text,
            panel.nameTextField.text,
            AndroidComponent.values()[panel.androidComponentComboBox.selectedIndex],
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

    override fun showModules(modules: List<String>) = modules.forEach { panel.moduleComboBox.addItem(it) }

    override fun selectModule(module: String) {
        panel.moduleComboBox.selectedItem = module
    }
}