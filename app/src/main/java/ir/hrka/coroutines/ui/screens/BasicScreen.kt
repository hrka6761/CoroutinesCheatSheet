package ir.hrka.coroutines.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ir.hrka.coroutines.AppBar
import ir.hrka.coroutines.R

@Composable
fun BasicScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            AppBar(
                title = "Basic",
                hasNavigationIcon = true,
                navHostController = navHostController,
                backgroundColor = R.color.basic_item_color
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}