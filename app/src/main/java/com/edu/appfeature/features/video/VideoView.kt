package com.edu.appfeature.features.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.edu.appfeature.R
import com.edu.appfeature.ui.theme.veryLightColor
import com.edu.appfeature.ui.util.extractVideoUrlId
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

/**
 * for example url = https://www.youtube.com/watch?v=rBvOSEj0CGo
 * @param youtubeVideoId = rBvOSEj0CGo (youtubeVideoId)
 * @param lifecycleOwner = LocalLifecycleOwner.current
 * this is the function for you-tub video play
 * example:
 *   YouTubePlayerWithId(
 *       youtubeVideoId = "rBvOSEj0CGo",
 *       lifecycleOwner = LocalLifecycleOwner.current
 *   )
 */
@Composable
fun YouTubePlayerWithId(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
) {
    AndroidView(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        factory = { context ->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(youtubeVideoId, 0f)
                    }
                })
            }
        }
    )
}

/**
 * for example url = https://www.youtube.com/watch?v=yxvaURdWJS8
 * @param youtubeVideoUrl = https://www.youtube.com/watch?v=4v-rIjHpyEk
 * @param lifecycleOwner = LocalLifecycleOwner.current
 * this is the function for you-tub video play
 * example:
 *   YouTubePlayerWithUrl(
 *       youtubeVideoId = "https://www.youtube.com/watch?v=yxvaURdWJS8",
 *       lifecycleOwner = LocalLifecycleOwner.current
 *   )
 */
@Composable
fun YouTubePlayerWithUrl(
    imageUrl: String,
    youtubeVideoUrl: String,
    lifecycleOwner: LifecycleOwner
) {
    var buttonVisible by remember { mutableStateOf(true) }
    val player = remember { mutableStateOf<YouTubePlayer?>(null) }

    val videoUrl = extractVideoUrlId(youtubeVideoUrl)

    if (videoUrl.isEmpty() || videoUrl == "invalid") {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.mipmap.img),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .size(360.dp)
                    .background(veryLightColor)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.SlowMotionVideo,
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(top = 25.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Video is not available!",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(1.dp)),
                factory = { context ->
                    YouTubePlayerView(context).apply {
                        lifecycleOwner.lifecycle.addObserver(this)
                        addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
//                                youTubePlayer.setPlaybackQuality(YouTubePlayer.PlaybackQuality.HD720)
                                player.value = youTubePlayer
                            }
                        })
                    }
                }
            )
            if (buttonVisible) {
                IconButton(
                    onClick = {
                        player.value?.loadVideo(videoUrl, 0f)
                        buttonVisible = false
                    },
                    enabled = player.value != null, // Enable the button when the player is ready
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Text(text = "", color = Color.White)
                }
            }
        }
    }
}