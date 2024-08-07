package ir.hrka.coroutines.data

import ir.hrka.coroutines.R

class VisualizeDataSource {

    private val screens: MutableList<ScreenDataModel> = mutableListOf()


    init {
        screens.add(
            ScreenDataModel(
                1,
                "Basic",
                "Describe simple coroutine",
                R.drawable.basic,
                R.color.basic_item_color,
                ""
            )
        )
    }


    fun getMainScreenItems(): List<ScreenDataModel> = screens

    fun getMainScreenItemsSize() = screens.size
}