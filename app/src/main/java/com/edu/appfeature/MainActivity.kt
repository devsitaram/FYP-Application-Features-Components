package com.edu.appfeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.edu.appfeature.ui.theme.AppFeatureTheme
import com.edu.appfeature.features.searchpage.SearchViewScreen

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
                    SearchViewScreen()
//                    LottieAnimationScreen()
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