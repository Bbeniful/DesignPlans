package com.bbeniful.designplans.mekisUI.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bbeniful.designplans.mekisUI.presentation.shape.CustomShapeWithTip
import kotlin.math.roundToInt

@Composable
fun InfoBox(onDismiss: () -> Unit, position: Offset) {
    val density = LocalDensity.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp


    val yOffset = with(density) {
        // Example: Adjust based on button's Y position, minus a constant like 200dp
        (position.y - ((screenHeight/2)+140).dp.toPx()).roundToInt()
    }

    val xOffset = with(density) {
        // Example: Adjust based on button's Y position, minus a constant like 200dp
        (position.x - (screenWidth-70.dp).toPx()).roundToInt()
    }
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .offset { // Positioning dialog relative to the button
                    IntOffset(xOffset,yOffset)
                }
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    Color.White,
                    shape = CustomShapeWithTip(
                        cornerRadius = 22.dp,
                        tipWidth = 24.dp,
                        tipHeight = 12.dp
                    )
                )


        ) {
            Column(
                modifier = Modifier.padding(all = 12.dp)
            ) {
                Text(text = "1")
                Text(text = "2")
                Text(text = "3")
                Text(text = "4")
                Text(text = "5")
                Text(text = "6")
                Text(text = "7")
            }

        }
    }
}

@Preview
@Composable
fun Preview() {
    InfoBox(onDismiss = { /*TODO*/ }, position = Offset.Zero)
}