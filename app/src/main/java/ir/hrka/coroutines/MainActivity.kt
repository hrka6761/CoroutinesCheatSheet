package ir.hrka.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ir.hrka.coroutines.data.VisualizeDataSource
import ir.hrka.coroutines.ui.screens.MainScreen
import ir.hrka.coroutines.ui.theme.CoroutinesTheme

class MainActivity : ComponentActivity() {

    private val dataSource = VisualizeDataSource()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoroutinesTheme {
                MainScreen(dataSource)
            }
        }

//        RunBlocking().fun1()
//        CoroutineScopeFunction().fun2()
//        CoroutineContextAndDispatchers().fun1()
//        CancellationCoroutines().fun8()
//        AsyncFunction().fun2()
//        AsynchronousFlow().fun19()
//        Channel().fun5()
//        ExceptionHandling().fun8()
    }
}