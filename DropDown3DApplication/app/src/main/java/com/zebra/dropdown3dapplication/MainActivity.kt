package com.zebra.dropdown3dapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zebra.dropdown3dapplication.ui.theme.DropDown3DApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DropDown3DApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF101010)
                ) {
                    Greeting("Select")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    DropDown(
        text = name,
        modifier = Modifier.padding(15.dp)
    ) {
        Text(
            text = "This is now revealed!", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DropDown(
    text: String,
    modifier: Modifier = Modifier,
    initiallyOpened: Boolean = false,
    content: @Composable () -> Unit
) {
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }
    val alpha = animateFloatAsState(
        targetValue = if (isOpen) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    val rotateX = animateFloatAsState(
        targetValue = if (isOpen) 0f else -90f, // X axis 0 to -90
        animationSpec = tween(
            durationMillis = 500
        )
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = text, color = Color.White, fontSize = 16.sp)
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open Close",
                tint = Color.White,
                modifier = Modifier.clickable {
                    isOpen = !isOpen
                }.scale(1f, if (isOpen) -1f else 1f) // scale X axis to 1 and Y axis either 1(need to dropdown open) or -1(need to dropdown close)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    transformOrigin = TransformOrigin(0.5f, 0f) // 50% of X axis and 0% of Y axis
                    rotationX = rotateX.value
                }
                .alpha(alpha.value)
        ) {
            content()
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DropDown3DApplicationTheme {
        Greeting("Android")
    }
}