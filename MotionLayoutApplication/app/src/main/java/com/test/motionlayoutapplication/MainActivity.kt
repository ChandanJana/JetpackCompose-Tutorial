package com.test.motionlayoutapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.test.motionlayoutapplication.ui.theme.MotionLayoutApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutApplicationTheme {
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
    Column {
        var progress by remember {
            mutableStateOf(0f)
        }
        val context = LocalContext.current
        val motionScene = remember {
            context.resources
                .openRawResource(R.raw.motion_scene)
                .readBytes()
                .decodeToString()
        }
        Log.d("TAGG", "motionScene $motionScene")
        ProfileHeader(progress = progress, motionScene)
        Spacer(modifier = Modifier.height(32.dp))
        Slider(
            value = progress,
            onValueChange = {
                Log.d("TAGG", "progress $it")
                progress = it
            },
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileHeader(progress: Float, motionScene: String) {
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        val propertiesProfile = motionProperties(id = "profile_pic")
        val propertiesName = motionProperties(id = "user_name")
        val propertiesBox = motionProperties(id = "box")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(propertiesBox.value.color("background"))
                .layoutId("box")
        )
        Image(
            painter = painterResource(id = R.drawable.picture),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = propertiesProfile.value.color("background"),
                    shape = CircleShape
                )
                .layoutId("profile_pic")
        )
        Text(
            text = "Chandan Jana",
            fontSize = 24.sp,
            color = propertiesName.value.color("background"),
            modifier = Modifier.layoutId("user_name")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MotionLayoutApplicationTheme {
        Greeting("Android")
    }
}