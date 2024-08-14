package ir.hrka.coroutines.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.hrka.coroutines.AppBar
import ir.hrka.coroutines.R
import ir.hrka.coroutines.data.CoroutineDataModel
import ir.hrka.coroutines.helpers.Constants.COROUTINE_CONTAINER_ID
import ir.hrka.coroutines.helpers.Constants.COROUTINE_SCOPE_TITLE_ID
import ir.hrka.coroutines.view_models.BasicViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@SuppressLint("MutableCollectionMutableState")
@Composable
fun BasicScreen(navHostController: NavHostController) {
    val basicViewModel: BasicViewModel = hiltViewModel()
    val list by basicViewModel.coroutines.collectAsState()

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
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
        ) {

            val constraintSet = ConstraintSet {
                val coroutineScopeTitle = createRefFor(COROUTINE_SCOPE_TITLE_ID)
                val coroutineContainer = createRefFor(COROUTINE_CONTAINER_ID)

                constrain(coroutineScopeTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }

                constrain(coroutineContainer) {
                    top.linkTo(coroutineScopeTitle.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            }
            val scope = rememberCoroutineScope{Dispatchers.IO}

            ConstraintLayout(constraintSet = constraintSet) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId(COROUTINE_SCOPE_TITLE_ID),
                    text = "Coroutine scope",
                    fontWeight = FontWeight.Bold
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp, bottom = 32.dp)
                        .layoutId(COROUTINE_CONTAINER_ID),
                    columns = GridCells.Adaptive(minSize = 128.dp)
                ) {
                    items(list) {
                        Coroutine(
                            coroutineData = CoroutineDataModel(
                                name = "${it.name}",
                                context = it.context
                            )
                        )
                    }
                }
            }

            LaunchedEffect(Unit) {
                scope.launch {
                    basicViewModel.getListOfCoroutines()
                }
            }
        }
    }
}

@Composable
fun Coroutine(coroutineData: CoroutineDataModel) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorResource(id = R.color.coroutine_item_color)
        ),
        elevation = CardDefaults.elevatedCardElevation(0.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            text = coroutineData.name
        )
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 16.dp, bottom = 16.dp),
            fontSize = 12.sp,
            style = TextStyle(lineHeight = 12.sp),
            text = ""
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }
}


@Composable
@Preview(showBackground = true)
fun BasicScreenPreview() {
    val navHostController = rememberNavController()
    BasicScreen(navHostController = navHostController)
}