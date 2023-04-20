package com.example.composeapiexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeapiexample.data.data_source.searchImage
import com.example.composeapiexample.data.model.Document
import com.example.composeapiexample.ui.theme.ComposeAPIExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (query, setQuery) = rememberSaveable { mutableStateOf("") }
            val list = mutableListOf<Document>()

            ComposeAPIExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    SearchPage(
                        query = query, setQuery = setQuery,
                        onSearch = {
                            searchImage(query = query) {
                                val docs = it["documents"] as List<*>
                                for (doc in docs) {
                                    val castedDoc =
                                        Document.fromJson(json = doc as Map<String, Any>)
                                    list.add(castedDoc)
                                    Log.d("TAG", "onCreate: list $list")
                                    Log.d("TAG", "------")
                                }
                            }
                        },
                        results = list.toString(),
                    )
                }
            }
        }
    }
}

@Composable
fun SearchPage(
    query: String,
    setQuery: (String) -> Unit,
    onSearch: () -> Unit,
    results: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(value = query, onValueChange = setQuery)
            IconButton(onClick = onSearch) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }
        }

        Text(
            text = results, modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
        )
    }
}
