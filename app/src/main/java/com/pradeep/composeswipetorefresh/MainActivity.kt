package com.pradeep.composeswipetorefresh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.pradeep.composeswipetorefresh.ui.theme.ComposeSwipeToRefreshTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSwipeToRefreshTheme {
                val viewModel = viewModel<MainViewModel>()
                val isLoading by viewModel.isLoading.collectAsState()
                val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

                SwipeRefresh(
                    state =swipeRefreshState,
                    onRefresh = viewModel::loadItems
                ) {
                    //should have scrollable content, else swipeRefresh won't be shown
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ){
                        items(50){

                            Card(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(text = buildAnnotatedString {
                                    append("Item - ")
                                    withStyle(style = SpanStyle(
                                        color = Color.Green
                                    )){
                                        append(it.toString())
                                    }
                                },
                                    modifier = Modifier.fillMaxSize()
                                        .padding(10.dp)
                                )
                            }

                        }
                    }

                }




            }
        }
    }
}

