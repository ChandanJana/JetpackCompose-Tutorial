package com.zebra.differentscreenapplication

import androidx.compose.ui.unit.Dp

/**
 * Created by Chandan Jana on 14-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
internal data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp,

    ){
    sealed class WindowType(){
        object Compact: WindowType()
        object Medium: WindowType()
        object Expanded: WindowType()
    }
}
