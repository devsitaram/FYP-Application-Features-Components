package com.edu.appfeature.features.coroutines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

private var results by mutableStateOf("")
private var duration by mutableStateOf("")

@Composable
fun Coroutines() {

    LaunchedEffect(key1 = results, key2 = duration, block = {
        results = results
        duration = duration
    })
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var num by remember { mutableIntStateOf(0) }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = results)
                Text(text = duration)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        parallelApiRequest(num = num)
                        num = if (num == 2) 0 else { num + 1 }
                    }
                ) {
                    Text(text = "Parallel")
                }

                Button(
                    onClick = {
                        sequentialApiResult()
                    }
                ) {
                    Text(text = "Sequential")
                }
            }
        }
    }
}

// parallel
private fun parallelApiRequest(num: Int) {
    CoroutineScope(Dispatchers.IO).launch {
        val executionTime = measureTimeMillis {

            val result1 = async { getResultFromApi1() }
            val result2 = async { getResultFromApi2() }

            when (num) {
                0 -> {
                    results =
                        "Both success result: \nAPI 1: ${result1.await()} \nAPI 2: ${result2.await()}"
                }

                1 -> {
                    val value1 = result1.await()
                    val value2 = result2.await()
                    results = if (result1 != null) {
                        "Either success result: \n$value1"
                    } else if (value2 != null) {
                        "Either success result: \n$value2"
                    } else {
                        "Both API calls failed."
                    }
                }

                else -> {
                    val value1 = result1.await()
                    results = try {
                        "Result is: ${getResultFromApi(value1)}"
                    } catch (e: CancellationException) {
                        "One is success and another is exception: \n$value1 \nException caught: ${e.message}"
                    }
                }
            }
        }
        duration = "Total elapsed time: $executionTime ms"
    }
}

// sequential
private fun sequentialApiResult() {
    CoroutineScope(Dispatchers.IO).launch {
        val executionTime = measureTimeMillis {
            downloadWebpage(num = 100)
        }
        results = "Sequential API call: \nExecution Time: $executionTime ms"
    }
}

// call api 1
private suspend fun getResultFromApi1(): String {
    delay(100)
    return "Api 1 is success."
}

// call api 2
private suspend fun getResultFromApi2(): String {
    delay(100)
    return "Api 2 is success."
}

// depend on call api 1
private suspend fun getResultFromApi(result: String): String {
    delay(100)
    return if (result == "Ap1 1") {
        "Api result 2 is success."
    } else {
        throw CancellationException("Api 2 call is distorted")
    }
}

// download web page
private fun downloadWebpage(num: Int) {
    for (i in 0 until num) {
        println("Total number of download page: $i")
    }
}