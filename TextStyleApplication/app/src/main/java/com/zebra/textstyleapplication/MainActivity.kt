package com.zebra.textstyleapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.zebra.textstyleapplication.ui.theme.TextStyleApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextStyleApplicationTheme {
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
    val fontFamily = FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_semi_bold, FontWeight.Bold),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_semi_bold, FontWeight.SemiBold),
        Font(R.font.lexend_extra_bold, FontWeight.ExtraBold),
        Font(R.font.lexend_extra_light, FontWeight.ExtraLight),
        Font(R.font.lexend_black, FontWeight.Black),

        )

    val annotatedString = buildAnnotatedString {

        append("By joining, you agree to the ")

        pushStringAnnotation(tag = "policy", annotation = "https://google.com/policy")

        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append("privacy policy")
        }
        pop()

        append(" and ")

        pushStringAnnotation(tag = "terms", annotation = "https://google.com/terms")

        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append("terms of use")
        }

        pop()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF101010))
    ) {
        ClickableText(
            text = annotatedString,
            style = TextStyle(color = Color.White, fontSize = 20.sp),
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "policy", start = offset, end = offset)
                    .firstOrNull()?.let {
                        Log.d("policy URL", it.item)
                    }

                annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset)
                    .firstOrNull()?.let {
                        Log.d("terms URL", it.item)
                    }
            }
        )

        Text(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp,
                    ),
                ) {
                    append("C")
                }
                append("handan ")

                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontSize = 50.sp,
                    )
                ) {
                    append("J")
                }
                append("ana")
            },
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = fontFamily,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextStyleApplicationTheme {
        Greeting("Android")
    }
}