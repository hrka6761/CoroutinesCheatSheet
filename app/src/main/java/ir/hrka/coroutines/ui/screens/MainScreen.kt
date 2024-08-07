package ir.hrka.coroutines.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hrka.coroutines.data.ScreenDataModel
import ir.hrka.coroutines.data.VisualizeDataSource


@Composable
fun MainScreen(dataSource: VisualizeDataSource) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(dataSource.getMainScreenItemsSize()) {
            ScreenItem(dataSource.getMainScreenItems()[it])
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "Coroutines visualizer") }
    )
}

@Composable
fun ScreenItem(screenDataModel: ScreenDataModel) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { },
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorResource(id = screenDataModel.color)
        ),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            text = screenDataModel.screenTitle
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            fontSize = 12.sp,
            style = TextStyle(lineHeight = 12.sp),
            text = screenDataModel.screenDescription
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = screenDataModel.screenIcon),
                contentDescription = ""
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen(VisualizeDataSource())
}