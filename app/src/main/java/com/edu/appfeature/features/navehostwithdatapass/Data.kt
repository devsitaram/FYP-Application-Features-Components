package com.edu.appfeature.features.navehostwithdatapass

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize

// some text or data transform from one page to another page
@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
class DataPojo(
    val title: String,
    val imageUri: String,
) : Parcelable

class DataViewModel : ViewModel() {
    var dataDetails by mutableStateOf<DataPojo?>(null)
    fun addSubjectDetails(newSubjectDetails: DataPojo) {
        dataDetails = newSubjectDetails
    }
}