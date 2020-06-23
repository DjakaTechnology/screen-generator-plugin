package ui.newscreen

import com.intellij.openapi.ui.ComboBox
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
    }

    override fun getPreferredSize() = Dimension(350, 110)
}
