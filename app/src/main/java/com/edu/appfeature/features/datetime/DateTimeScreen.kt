@file:Suppress("NAME_SHADOWING")

package com.edu.appfeature.features.datetime

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.Date

// Creating a composable function to
// create two Images and a spacer between them
// Calling this function as content
// in the above function
@Composable
fun DateTimeViewScreen() {

    // Fetching the Local Context
    val context = LocalContext.current

    // for year, month and day
    val year: Int
    val month: Int
    val day: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    var mDate by remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, mDayOfMonth: Int ->
            mDate = "$mDayOfMonth/${month + 1}/$year"
        }, year, month, day
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // click displays/shows the DatePickerDialog
        Button(
            onClick = {
                mDatePickerDialog.show()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor =  Color(0XFF0F9D58)
            )
        ) {
            Text(text = "Open Date Picker", color = Color.White)
        }

        // Adding a space of 100dp height
        Spacer(modifier = Modifier.size(100.dp))

        // Displaying the mDate value in the Text
        Text(text = "Current Date: $mDate", fontSize = 30.sp, textAlign = TextAlign.Center)
    }
}