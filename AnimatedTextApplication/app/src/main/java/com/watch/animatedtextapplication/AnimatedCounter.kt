package com.watch.animatedtextapplication

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

/**
 * Created by Chandan Jana on 25-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter(
    count: Int,
    reverseAnimate: Boolean,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    var oldCount by remember {
        mutableStateOf(count)
    }
    Log.d("TAGG", "oldCount $oldCount" )
    Log.d("TAGG", "count $count" )
    Log.d("TAGG", "reverseAnimate $reverseAnimate" )

    SideEffect {
        oldCount = count
    }

    Row(modifier = modifier) {
        val countString = count.toString()
        val oldCountString = oldCount.toString()

        for(i in countString.indices) {
            val oldChar = oldCountString.getOrNull(i)
            val newChar = countString[i]
            val char = if(oldChar == newChar) {
                oldCountString[i]
            } else {
                countString[i]
            }
            if (reverseAnimate) {
                AnimatedContent(
                    targetState = char,
                    transitionSpec = {
                        //EnterTransition Minus height to ExitTransition Plus height.Up to Down
                        slideInVertically { -it } with slideOutVertically { it }
                    }
                ) { char ->
                    Text(
                        text = char.toString(),
                        style = style,
                        softWrap = false
                    )
                }
            }else{
                AnimatedContent(
                    targetState = char,
                    transitionSpec = {
                        //EnterTransition Plus height to ExitTransition Minus height, Down to Up
                        slideInVertically { it } with slideOutVertically { -it }
                    }
                ) { char ->
                    Text(
                        text = char.toString(),
                        style = style,
                        softWrap = false
                    )
                }
            }
        }
    }
}