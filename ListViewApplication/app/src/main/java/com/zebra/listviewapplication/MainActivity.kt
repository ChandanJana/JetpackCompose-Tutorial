package com.zebra.listviewapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zebra.listviewapplication.ui.theme.ListViewApplicationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListViewApplicationTheme {
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
    /*val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 1..100){
            Text(
                text = "item $i",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth().
                        padding(vertical = 24.dp)
            )
        }
    }*/
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp),
    ) {

        itemsIndexed(
            listOf(
                "This",
                "is",
                "jetpack",
                "compose",
                "tutorial",
                "for",
                "beginner",
                "form",
                "our",
                "course"
            )
        ) { index, name ->
            var colo: Color = Color(Random.nextInt(), Random.nextInt(), Random.nextInt(), alpha = 5)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 5.dp),
                border = BorderStroke(width = 2.dp, color = Color.Gray),
                elevation = 10.dp
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFFF518A0),
                                    Color(0xFFB232BD)
                                )
                            )
                        )
                    //.padding(all = 32.dp)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = name,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                }
            }

        }
        /*items(100) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 5.dp),
                border = BorderStroke(width = 2.dp, color = Color.Gray),
                elevation = 10.dp
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFFF518A0),
                                    Color(0xFFB232BD)
                                )
                            )
                        )
                    //.padding(all = 32.dp)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "item $it",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                }
            }
        }*/

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListViewApplicationTheme {
        Greeting("Android")
    }
}