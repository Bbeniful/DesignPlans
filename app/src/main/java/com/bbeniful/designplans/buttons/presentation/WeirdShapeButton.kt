package com.bbeniful.designplans.buttons.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bbeniful.designplans.core.ui.theme.darkGreen
import com.bbeniful.designplans.core.ui.theme.purple

@Composable
fun WeirdShapeButton(width: Dp = 150.dp, height: Dp = 80.dp, onClick: () -> Unit = {}, colors: List<Color>, content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.clickable {
        onClick()
    }) {
        Box(
            modifier = Modifier
                .size(width = width, height = height)
                .offset(
                    x = 8.dp,
                    y = 8.dp
                )
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(topStart = 50.dp, bottomEnd = 50.dp)
                )
        )

        Box(
            modifier = Modifier
                .size(width = width, height = height)
                .background(
                    brush = Brush.horizontalGradient(colors = colors),
                    shape = RoundedCornerShape(topStart = 50.dp, bottomEnd = 50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)) {
        WeirdShapeButton(colors = listOf(purple, darkGreen)) {
            Text(text = "Hello")
        }
    }

}