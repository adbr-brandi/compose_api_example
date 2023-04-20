package com.example.composeapiexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeapiexample.data.data_source.searchImage
import com.example.composeapiexample.data.model.Document
import com.example.composeapiexample.ui.theme.Black500
import com.example.composeapiexample.ui.theme.ComposeAPIExampleTheme
import com.example.composeapiexample.ui.theme.LightGrey100

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
        modifier = Modifier.fillMaxSize()

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)

        ) {
            Box(
                Modifier.weight(6f)
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = setQuery,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = LightGrey100,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
            }
            Box(
                Modifier.weight(1f)
            ) {
                Button(
                    onClick = onSearch,
                    contentPadding = PaddingValues(0.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Black500
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }


        }
        Divider()
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(results) {
                AsyncImage(
                    model = it.imageURL,
                    contentDescription = it.displaySitename,
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .background(LightGrey100),
                )
            }
        }
    }
}
