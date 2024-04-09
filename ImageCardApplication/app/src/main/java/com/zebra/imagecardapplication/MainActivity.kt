package com.zebra.imagecardapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zebra.imagecardapplication.ui.theme.ImageCardApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageCardApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .padding(10.dp)
                    ) {
                        ImageCard(
                            painter = painterResource(id = R.drawable.img),
                            contentDrscription = "Hello",
                            title = "My Dog"
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDrscription: String,
    title: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = contentDrscription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ),
                            startY = 200f
                        ) // Brush used for gradient effect
                    )
            ) {

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageCardApplicationTheme {
        ImageCard(
            painter = painterResource(id = R.drawable.img),
            contentDrscription = "Hello",
            title = "My Dog"
        )
    }
}