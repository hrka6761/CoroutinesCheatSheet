package ir.hrka.coroutines.data

import kotlinx.coroutines.CoroutineScope

data class CoroutineDataModel(
    var name: String,
    var context: CoroutineScope,
)
