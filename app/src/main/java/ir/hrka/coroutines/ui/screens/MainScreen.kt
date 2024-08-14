package ir.hrka.coroutines.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.hrka.coroutines.AppBar
import ir.hrka.coroutines.R
import ir.hrka.coroutines.data.ScreenDataModel
import ir.hrka.coroutines.data.VisualizeDataSource


@Composable
fun MainScreen(
    navHostController: NavHostController,
    dataSource: VisualizeDataSource
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.main_app_bar_title),
                hasNavigationIcon = false,
                navHostController = navHostController,
                backgroundColor = R.color.white
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
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                columns = GridCells.Adaptive(minSize = 128.dp)
            ) {
                items(dataSource.getMainScreenItemsSize()) {
                    ScreenItem(
                        navHostController = navHostController,
                        itemData = dataSource.getMainScreenItems()[it]
                    )
                }
            }
        }
    }
}

@Composable
fun ScreenItem(
    navHostController: NavHostController,
    itemData: ScreenDataModel
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { navHostController.navigate(itemData.screenDestination) },
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorResource(id = itemData.itemColor)
        ),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            text = itemData.screenTitle
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            fontSize = 12.sp,
            style = TextStyle(lineHeight = 12.sp),
            text = itemData.screenDescription
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = itemData.screenIcon),
                contentDescription = ""
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    val navHostController = rememberNavController()
    MainScreen(navHostController, VisualizeDataSource())
}