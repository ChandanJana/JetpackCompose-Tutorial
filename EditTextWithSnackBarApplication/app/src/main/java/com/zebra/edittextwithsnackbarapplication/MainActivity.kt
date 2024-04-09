package com.zebra.edittextwithsnackbarapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zebra.edittextwithsnackbarapplication.ui.theme.EditTextWithSnackBarApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EditTextWithSnackBarApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val state = rememberScaffoldState()
    var textState by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = state
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = textState,
                label = { Text(text = "Your name") },
                onValueChange = {
                    textState = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (textState.isNotBlank()) {
                    scope.launch {
                        val snackbarResult = state.snackbarHostState.showSnackbar(
                            textState, actionLabel = "Go", duration = SnackbarDuration.Long
                        )
                        when (snackbarResult) {
                            SnackbarResult.Dismissed -> {
                                //it will come when snackbar will be dismissed automatically
                                Log.d("TAGG", "Snackbar dismissed")
                            }

                            SnackbarResult.ActionPerformed -> {
                                //it will come when user pressed the action label
                                Log.d("TAGG", "Snackbar ActionPerformed")
                            }
                        }
                    }
                }

            }) {
                Text(text = "Click", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EditTextWithSnackBarApplicationTheme {
        Greeting("Android")
    }
}