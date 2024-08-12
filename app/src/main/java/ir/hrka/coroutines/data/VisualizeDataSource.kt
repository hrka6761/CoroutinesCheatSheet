package ir.hrka.coroutines.data

import ir.hrka.coroutines.R
import ir.hrka.coroutines.helpers.Screens.Basic

class VisualizeDataSource {

    private val screens: MutableList<ScreenDataModel> = mutableListOf()


    init {
        screens.add(
            ScreenDataModel(
                id = 1,
                screenTitle = "Basic",
                screenDescription = "Describe simple coroutine",
                screenIcon = R.drawable.basic,
                itemColor = R.color.basic_item_color,
                screenDestination = Basic()
            )
        )
    }


    fun getMainScreenItems(): List<ScreenDataModel> = screens

    fun getMainScreenItemsSize() = screens.size
}