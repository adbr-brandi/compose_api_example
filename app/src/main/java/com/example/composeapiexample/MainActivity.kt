package com.example.composeapiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.composeapiexample.data.data_source.getJSONData
import com.example.composeapiexample.ui.theme.ComposeAPIExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val list = mutableListOf<String>()
            val result = getJSONData(query = "bora", list = list)
            print(result)

            ComposeAPIExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Greeting(list.toString())
                }
            }
        }
    }
}

@Composable
fun Greeting(result: String) {
    Text(
        text = result,
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
    )
}
