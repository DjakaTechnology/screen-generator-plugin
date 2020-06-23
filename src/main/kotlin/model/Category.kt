package model

import java.io.Serializable
import java.util.*

private const val UNNAMED_CATEGORY = "UnnamedCategory"

data class Category(
    var id: Int = Random().nextInt(),
    var name: String = UNNAMED_CATEGORY,
    var screenElements: MutableList<ScreenElement> = defaultScreenElements()
) : Serializable {

    override fun toString() = name
}