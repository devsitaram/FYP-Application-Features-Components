package com.edu.appfeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.edu.appfeature.features.IntroScreenView
import com.edu.appfeature.features.LottieAnimation.LottieAnimationScreen
import com.edu.appfeature.features.datetime.DateTimeViewScreen
import com.edu.appfeature.features.floating_button_bar.FloatingBtnNavBarViewScreen
import com.edu.appfeature.features.navehostwithdatapass.NavigateDataPassViewScreen
import com.edu.appfeature.ui.theme.AppFeatureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val windows = this.window
//        windows.statusBarColor = Color.White

        setContent {
            AppFeatureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FloatingBtnNavBarViewScreen()
//                    IntroScreenView()
//                    DateTimeViewScreen()
//                    LottieAnimationScreen()
//                    SearchLists()
//                    val navHostController = rememberNavController()
//                    NavigateDataPassViewScreen(navHostController)
//                    VideoViewScreen()
//                    val videoUrl = "https://www.youtube.com/watch?v=fdvdd123"
//                    val videoId = getVideoId(videoUrl)
//                    Log.e("videoUrl: ", videoUrl)
//                    Log.e("videoId: ", videoId)
//                    println(videoId)
                }
            }
        }
    }
}


fun searchList(list: List<Item>, query: String): List<Item> {
    return list.filter { item ->
        item.name.contains(query, ignoreCase = true)
    }
}

data class Item(val name: String, val description: String)

val itemList = listOf(
    Item("Apple", "A fruit"),
    Item("Banana", "Another fruit"),
    Item("Carrot", "A vegetable"),
    Item("Dog", "An animal")
)

@Composable
fun SearchLists() {

    val query = "fruit"
    val filteredItems = searchList(itemList, query)

    Column {
        for (item in filteredItems) {
            Text("Name: ${item.name}, Description: ${item.description}")
        }
    }
}