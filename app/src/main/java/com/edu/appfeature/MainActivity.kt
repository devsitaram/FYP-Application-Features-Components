package com.edu.appfeature

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.edu.appfeature.features.video.VideoViewScreen
import com.edu.appfeature.ui.theme.AppFeatureTheme
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppFeatureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VideoViewScreen()
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