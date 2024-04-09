package com.zebra.animationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zebra.animationapplication.ui.theme.AnimationApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
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
    var sizeBox by remember { mutableStateOf(200.dp) }
    val size by animateDpAsState(targetValue = sizeBox,
    tween(
        durationMillis = 3000,
        delayMillis = 300,
        easing = LinearOutSlowInEasing
    )
    /*spring(
        Spring.DampingRatioLowBouncy
    )*/
    /*keyframes {
        durationMillis = 5000
        sizeBox at 0 with LinearEasing
        sizeBox * 1.5f at 1000 with FastOutLinearInEasing
        sizeBox * 2f at 5000
    }*/

    )
    var infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Green,
        targetValue = Color.Yellow,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 1000
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeBox += 50.dp
        }) {
            Text(text = "InCrease size")
            
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimationApplicationTheme {
        Greeting("Android")
    }
}