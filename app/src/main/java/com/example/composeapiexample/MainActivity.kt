package com.example.composeapiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.composeapiexample.data.data_source.searchImage
import com.example.composeapiexample.data.model.Document
import com.example.composeapiexample.ui.main.SearchPage
import com.example.composeapiexample.ui.theme.ComposeAPIExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (query, setQuery) = rememberSaveable { mutableStateOf("") }
            val list = rememberSaveable { mutableStateOf(listOf<Document>()) }

            ComposeAPIExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    SearchPage(
                        query = query, setQuery = setQuery,
                        onSearch = { searchImage(query = query, documents = list) },
                        results = list.value,
                    )
                }
            }
        }
    }
}


