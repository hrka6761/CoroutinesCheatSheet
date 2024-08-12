package ir.hrka.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ir.hrka.coroutines.ui.theme.CoroutinesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoroutinesTheme {
                MainContainer()
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