package com.example.composeapiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.composeapiexample.data.data_source.searchImage
import com.example.composeapiexample.ui.main.SearchPage
import com.example.composeapiexample.ui.main.SearchPageViewModel
import com.example.composeapiexample.ui.theme.ComposeAPIExampleTheme

class MainActivity : ComponentActivity() {
    private val vm by viewModels<SearchPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAPIExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    SearchPage(
                        query = vm.query, setQuery = { vm.query = it },
                        onSearch = { searchImage(query = vm.query, documents = vm.list) },
                        results = vm.list.value,
                    )
                }
            }
        }
    }
}


