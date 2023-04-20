package com.example.composeapiexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeapiexample.data.data_source.searchImage
import com.example.composeapiexample.data.model.Document
import com.example.composeapiexample.ui.theme.ComposeAPIExampleTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (query, setQuery) = rememberSaveable { mutableStateOf("") }
            val list = mutableStateListOf<Document>()

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
                                }
                            }
                        },
                        results = list,
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
    results: List<Document>,
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
        Spacer(modifier = Modifier.size(20.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 10.dp),
        ) {
            items(items = results) {
                Card(modifier = Modifier.fillMaxWidth(), elevation = 10.dp) {
                    Row() {
                        Text(it.imageURL)
                    }
                }
            }
        }
    }
}
