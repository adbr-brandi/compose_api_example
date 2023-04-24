package com.example.composeapiexample.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composeapiexample.data.model.Document

class SearchPageViewModel : ViewModel() {
    var query by mutableStateOf("")
    val list = mutableStateOf(listOf<Document>())
}