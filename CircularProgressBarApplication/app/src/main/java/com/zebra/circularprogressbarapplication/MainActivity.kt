package com.zebra.circularprogressbarapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zebra.circularprogressbarapplication.ui.theme.CircularProgressBarApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularProgressBarApplicationTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressBar(
                        percentage = .8f,
                        number = 100,
                        fontSize = 25.sp,
                        radius = 100.dp,
                        animationDelay = 0
                    )
                }
            }
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    animationDuration: Int = 1000,
    animationDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true // set true value only for first time that is why it is in LaunchedEffect
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color, startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(width = 20f, cap = StrokeCap.Round)
            )
        }
        Text(
            text = number.toString(),
            modifier = Modifier.align(alignment = Alignment.Center),
            fontSize = fontSize
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CircularProgressBarApplicationTheme {
        CircularProgressBar(0.1f, 100, 12.sp)
    }
}