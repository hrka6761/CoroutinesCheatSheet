package ir.hrka.coroutines.view_models

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hrka.coroutines.data.CoroutineDataModel
import ir.hrka.coroutines.helpers.Log.printYellow
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class BasicViewModel @Inject constructor() : ViewModel() {

    private val _coroutines: MutableStateFlow<List<CoroutineDataModel>> =
        MutableStateFlow(emptyList())
    val coroutines: StateFlow<List<CoroutineDataModel>> = _coroutines

    fun getListOfCoroutines() {
        val job = Job()
        val parentCoroutineName = CoroutineName("Parent")
        val child1CoroutineName = CoroutineName("Child1")
        val child2CoroutineName = CoroutineName("Child2")
        val threads = Dispatchers.IO
        val coroutineContext = threads + job + parentCoroutineName

        runBlocking(coroutineContext) {
            // Add the initial parent coroutine
            val parentCoroutineData = CoroutineDataModel(
                name = this.coroutineContext[CoroutineName.Key].toString(),
                context = this
            )
            _coroutines.value += parentCoroutineData

            // Launch a child coroutine
            launch(child1CoroutineName) {
                val childCoroutineData = CoroutineDataModel(
                    name = this.coroutineContext[CoroutineName.Key].toString(),
                    context = this
                )
                // Add child coroutine to the list
                _coroutines.value += childCoroutineData

                // Delay for some time
                delay(2000)

                // Modify the element after delay
                val updatedList = _coroutines.value.map { coroutineData ->
                    if (coroutineData.name == childCoroutineData.name) {
                        coroutineData.copy(name = "Completed Child1") // Change the name
                    } else {
                        coroutineData
                    }
                }

                // Update the StateFlow with the new list
                _coroutines.value = updatedList
            }

            launch(child2CoroutineName) {
                val childCoroutineData = CoroutineDataModel(
                    name = this.coroutineContext[CoroutineName.Key].toString(),
                    context = this
                )
                // Add child coroutine to the list
                _coroutines.value += childCoroutineData

                // Delay for some time
                delay(4000)

                // Modify the element after delay
                val updatedList = _coroutines.value.map { coroutineData ->
                    if (coroutineData.name == childCoroutineData.name) {
                        coroutineData.copy(name = "Completed Child2") // Change the name
                    } else {
                        coroutineData
                    }
                }

                // Update the StateFlow with the new list
                _coroutines.value = updatedList
            }
        }
    }
}