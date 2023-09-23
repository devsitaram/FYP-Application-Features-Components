package com.edu.appfeature.features.LottieAnimation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.edu.appfeature.R
import kotlinx.coroutines.delay

@Composable
fun LottieAnimationScreen() {

    var isPlaying by remember { mutableStateOf(true) }
    var isTextVisible by remember { mutableStateOf(false) }

    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_splash))

    val progress by animateLottieCompositionAsState(
        composition = compositionResult.value,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever,
        speed = 1f
    )

    LaunchedEffect(!isTextVisible) {
        delay(2000) // Delay for 4 seconds after stopping the animation
        isTextVisible = true
    }
    LaunchedEffect(isPlaying) {
        delay(4000)
        isPlaying = false
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            composition = compositionResult.value,
            progress = progress,
            modifier = Modifier.fillMaxSize()
        )

        if (isTextVisible) {
            Text(
                text = "Welcome to Gameyo",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Cursive,
                    color = Color.Unspecified
                )
            )
        }
    }
}