package ir.hrka.coroutines

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hrka.coroutines.data.VisualizeDataSource
import ir.hrka.coroutines.helpers.Screens.Main
import ir.hrka.coroutines.ui.screens.AppBar
import ir.hrka.coroutines.ui.screens.MainScreen


@Composable
fun MainContainer(activity: Activity) {
    val navController = rememberNavController()
    val dataSource = VisualizeDataSource()

    MainNavHost(
        activity = activity,
        navController = navController,
        dataSource = dataSource
    )
}

@Composable
fun MainNavHost(
    activity: Activity,
    navController: NavHostController,
    dataSource: VisualizeDataSource
) {
    Scaffold(
        topBar = { AppBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = Main()
            ) {
                composable(route = Main()) {
                    MainScreen(dataSource = dataSource)
                }
            }
        }
    }
}