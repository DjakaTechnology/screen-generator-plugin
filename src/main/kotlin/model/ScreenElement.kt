package model

import util.toSnakeCase
import java.io.Serializable

private const val UNNAMED_ELEMENT = "UnnamedElement"

data class ScreenElement(var name: String = "",
                         var template: String = "",
                         var fileType: FileType = FileType.KOTLIN,
                         var fileNameTemplate: String = "") : Serializable {

    override fun toString() = name

    fun body(screenName: String, packageName: String) =
            template.replaceVariables(screenName, packageName)

    fun fileName(screenName: String, packageName: String) =
            fileNameTemplate.replaceVariables(screenName, packageName).run {
                if (fileType == FileType.LAYOUT_XML)
                    toLowerCase()
                else
                    this
            }

    private fun String.replaceVariables(screenName: String, packageName: String) =
            replace(Variable.NAME.value, screenName)
                    .replace(Variable.NAME_SNAKE_CASE.value, screenName.toSnakeCase())
                    .replace(Variable.NAME_LOWER_CASE.value, screenName.decapitalize())
                    .replace(Variable.SCREEN_ELEMENT.value, name)
                    .replace(Variable.PACKAGE_NAME.value, packageName)

    companion object {
        fun getDefault() = ScreenElement(UNNAMED_ELEMENT, FileType.KOTLIN.defaultTemplate, FileType.KOTLIN, FileType.KOTLIN.defaultFileName)
    }
}