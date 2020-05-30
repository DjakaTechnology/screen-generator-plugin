package ui.newscreen

import com.intellij.openapi.ui.ComboBox
import model.AndroidComponent
import model.Category
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField


class NewScreenPanel : JPanel() {

    val nameTextField = JTextField()
    val packageTextField = JTextField()

    val categoryComboBox = ComboBox<Category>()
    val androidComponentComboBox = ComboBox<AndroidComponent>(AndroidComponent.values())
    val moduleComboBox = ComboBox<String>()

    init {
        layout = GridLayout(0, 2)
        add(JLabel("Category:"))
        add(categoryComboBox)
        add(JLabel("Module:"))
        add(moduleComboBox)
        add(JLabel("Package:"))
        add(packageTextField)
        add(JLabel("Name:"))
        add(nameTextField)
        add(JLabel("Android Component:"))
        add(androidComponentComboBox)
    }

    override fun getPreferredSize() = Dimension(350, 110)
}
