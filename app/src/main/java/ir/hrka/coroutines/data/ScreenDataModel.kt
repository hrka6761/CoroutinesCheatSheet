package ir.hrka.coroutines.data

data class ScreenDataModel(
    val id: Int,
    val screenTitle: String,
    val screenDescription: String,
    val screenIcon: Int,
    val itemColor: Int,
    val screenDestination: String
)