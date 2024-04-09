package com.zebra.modifierapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zebra.modifierapplication.ui.theme.ModifierApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModifierApplicationTheme {
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(color = Color.Green)
            .fillMaxWidth()
            //.height(200.dp)
            //.requiredHeight(200.dp) // for exact height
            .fillMaxHeight(.5f)
            .border(2.dp, color = Color.Magenta)
            .padding(5.dp)
            .border(2.dp, color = Color.Blue)
            .padding(5.dp)
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier.clickable {

            }
                .offset(20.dp,50.dp),// offset will the view 30 dp along wth x axis and 50 dp along with y axis
            fontSize = 5.sp
        )

        Text(
            text = "Hi $name!",
            modifier = modifier,
            fontSize = 5.sp
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ModifierApplicationTheme {
        Greeting("Android")
    }
}