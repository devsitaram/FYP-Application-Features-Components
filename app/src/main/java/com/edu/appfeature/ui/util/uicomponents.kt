package com.edu.appfeature.ui.util

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun TextView(text: String, style: TextStyle, modifier: Modifier) {
    Text(text = text, style = style, modifier = modifier)
}