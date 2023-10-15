package com.edu.appfeature.features.coroutines

import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class Coroutines {


    // sequential
    private fun sequentialApiResult(){
        CoroutineScope(Dispatchers.IO).launch {
            val executionTime = measureTimeMillis {
                val count = 20
                for (i in 0 until count) {
                    println("Total count: $i")
                }
            }
            println("Execution Time: $executionTime ms")
        }
    }

    // parallel
    private fun parallelApiRequest() {
        CoroutineScope(Dispatchers.IO).launch {
            val executionTime = measureTimeMillis {
                val result1 = async {
                    Log.e("Result1", "debug: launching job1: ${Thread.currentThread()}")
                    getResultFromApi1()
                }.await()

                val result2 = async {
                    Log.e("Result1", "debug: launching job2: ${Thread.currentThread()}")
                    getResultFromApi2(result1)
                }.await()
                println("Result 2 is :$result2")
            }
            Log.e("Debug:", "Debug: Total elapsed time: $executionTime milliSeconds")
        }
    }

    private suspend fun getResultFromApi1(): String {
        delay(1000)
        return "Api 1 is success."
    }

    private suspend fun getResultFromApi2(result1: String): String {
        delay(1000)
        if(result1.isNotEmpty()){
            return "Api 1 is success."
        }
        throw CancellationException("Result 1 is empty")
    }
}