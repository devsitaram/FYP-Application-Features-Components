package com.edu.appfeature.ui.util

import androidx.compose.runtime.Composable
import java.util.regex.Pattern

// check the text validation (username)
fun textValidation(username: String?): Boolean? {
    val nameRegex = Regex("[A-Za-z\\s]+")
    return username?.matches(nameRegex)
}

// check the input date validation
fun dateValidation(date: String?): Boolean? {
    val dateRegex = Regex("""\d{2}-\d{2}-\d{4}""")
    return date?.matches(dateRegex)
}

// check the username validation
fun emailValidation(email: String): Boolean {
    // get text fields text
    val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+.+[a-z]+")
    return email.matches(emailPattern)
}

// check the username validation
fun passwordValidation(password: String): Boolean {
    val nameRegex = Regex("[a-zA-Z0-9]")
    return password.matches(nameRegex)
}

// check the number where int and double both support
fun numberValidation(number: String?): Boolean {
    val intRegex = Regex("-?\\d+")
    val doubleRegex = Regex("-?\\d+\\.\\d+")
    return number?.matches(intRegex) == true || number?.matches(doubleRegex) == true
}

// Function to extract video ID from a YouTube URL
fun extractVideoUrlId(youtubeUrl: String): String {
    val pattern =
        "(?<=/videos/|embed/|youtu.be/|/e/|watch\\?v=|/v/|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#&?\\n]*"
    val compiledPattern = Pattern.compile(pattern)
    val matcher = compiledPattern.matcher(youtubeUrl)
    return if (matcher.find()) {
        matcher.group()
    } else if (youtubeUrl.isEmpty()) {
        return ""
    } else {
        return "invalid"
    }
}

fun getVideoId(videoUrl: String): String {
    val pattern = "(?<=\\?v=)[a-zA-Z0-9_-]*"
    val compiledPattern = Pattern.compile(pattern)
    val matcher = compiledPattern.matcher(videoUrl)
    return if (matcher.find()) {
        matcher.group()
    } else {
        ""
    }
}