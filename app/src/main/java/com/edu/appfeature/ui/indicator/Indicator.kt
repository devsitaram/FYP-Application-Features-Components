package com.edu.appfeature.ui.indicator

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Modifier.size(50.dp)
@Composable
fun CircularIndicator(color: Color, modifier: Modifier) {
    CircularProgressIndicator(
        color = color,
        strokeWidth = 4.dp,
        modifier = modifier,
    )
}