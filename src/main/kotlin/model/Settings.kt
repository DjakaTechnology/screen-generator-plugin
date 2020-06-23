package model

import java.io.Serializable

private val DEFAULT_MVP_TEMPLATE =
        "package ${Variable.PACKAGE_NAME.value}\n\n\nclass ${Variable.NAME.value}Activity: AppCompatActivity"
private val DEFAULT_VIEW_TEMPLATE = "package ${Variable.PACKAGE_NAME.value}\n\ninterface ${Variable.NAME.value}${Variable.SCREEN_ELEMENT.value}"

fun defaultScreenElements() = mutableListOf(
        ScreenElement("MVP", DEFAULT_MVP_TEMPLATE, FileType.KOTLIN, "${Variable.NAME.value}Activity"),
        ScreenElement("Presenter", FileType.KOTLIN.defaultTemplate, FileType.KOTLIN, FileType.KOTLIN.defaultFileName),
        ScreenElement("View", DEFAULT_VIEW_TEMPLATE, FileType.KOTLIN, FileType.KOTLIN.defaultFileName),
        ScreenElement("layout", FileType.LAYOUT_XML.defaultTemplate, FileType.LAYOUT_XML, FileType.LAYOUT_XML.defaultFileName)
)

private fun defaultCategories() = mutableListOf(
        Category(1, "Activity", defaultScreenElements()),
        Category(2, "Fragment", defaultScreenElements()),
        Category(3, "Adapter", defaultScreenElements())
)

data class Settings(
        var categories: MutableList<Category> = defaultCategories(),
        var currentlySelectedCategory: Category = categories[0]
) : Serializable