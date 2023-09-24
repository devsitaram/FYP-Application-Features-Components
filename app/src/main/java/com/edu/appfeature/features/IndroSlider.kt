package com.edu.appfeature.features

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.edu.appfeature.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreenView() {

    val context = LocalContext.current
    val pagerState = rememberPagerState()
    val list = getList()

    val isNextVisible = remember { derivedStateOf { pagerState.currentPage < list.size - 1 } }
    val isPrevVisible = remember { derivedStateOf { pagerState.currentPage > 0 } }
    val isContinues = remember { derivedStateOf { pagerState.currentPage == list.size - 1 } }

    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.75f)
        ) {
            HorizontalPager(
                state = pagerState,
                verticalAlignment = Alignment.CenterVertically,
                count = list.size
            ) { currentPage ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = list[currentPage].title,
                        style = MaterialTheme.typography.h4,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    AsyncImage(
                        model = list[currentPage].image,
                        contentDescription = null, modifier = Modifier
                            .height(380.dp)
                            .width(300.dp)
                    )

                    Text(
                        text = list[currentPage].description,
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth(.6f)
                            .align(
                                Alignment.CenterHorizontally
                            )
                    )
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(vertical = 26.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            if (isPrevVisible.value && !isContinues.value) {
                Button(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                ) {
                    Text(text = "Prev")
                }
            }
            if (isNextVisible.value && !isContinues.value) {
                Button(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                ) {
                    Text(text = "Next")
                }
            }
            if (isContinues.value) {
                Button(
                    onClick = {
                        // Navigate to the login screen
//                        navController.navigate("login")
                        Toast.makeText(context, "complete", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(text = "Continue")
                }
            }
        }
    }
}

data class HorizontalPagerContent(
    val title: String, @DrawableRes val image: Int, val description: String
)

fun getList(): List<HorizontalPagerContent> {
    return listOf(
        HorizontalPagerContent(
            title = "Verified",
            image = R.drawable.first,
            description = "Verification is an extra or final bit of proof that establishes something is true"
        ),
        HorizontalPagerContent(
            title = "Make it simple",
            image = R.drawable.second,
            description = "We pay attention to all of your payments and find way for saving your money"
        ),
        HorizontalPagerContent(
            title = "New Banking",
            image = R.drawable.third,
            description = "Free Advisory service,mobile banking application,visa"
        ),
        HorizontalPagerContent(
            title = "Zero Fees",
            image = R.drawable.fourth,
            description = "Bank your life,We create something new you have never seen before"
        )
    )
}