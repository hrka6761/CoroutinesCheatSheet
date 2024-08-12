package ir.hrka.coroutines

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hrka.coroutines.data.VisualizeDataSource
import ir.hrka.coroutines.helpers.Screens.Main
import ir.hrka.coroutines.helpers.Screens.Basic
import ir.hrka.coroutines.ui.screens.BasicScreen
import ir.hrka.coroutines.ui.screens.MainScreen


@Composable
fun MainContainer() {
    val navController = rememberNavController()
    val dataSource = VisualizeDataSource()

    MainNavHost(
        navHostController = navController,
        dataSource = dataSource
    )
}

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    dataSource: VisualizeDataSource
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navHostController,
        startDestination = Main()
    ) {
        // Main screen
        composable(route = Main()) {
            MainScreen(
                navHostController = navHostController,
                dataSource = dataSource
            )
        }
        // Basic screen
        composable(route = Basic()) {
            BasicScreen(navHostController = navHostController)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    hasNavigationIcon: Boolean,
    navHostController: NavHostController,
    backgroundColor: Int
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = backgroundColor)
        ),
        navigationIcon = {
            if (hasNavigationIcon)
                IconButton(
                    onClick = { navHostController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                }
        }
    )
}