package com.bbeniful.designplans.buttons.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun DoubleColorButton(
    onClick: () -> Unit = {},
    mainColor: Color,
    secondColor: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.clickable {
        onClick()
    }) {
        Box(
            modifier = Modifier
                .zIndex(1f)
                .size(width = 150.dp, height = 60.dp)
                .offset(
                    x = 5.dp,
                    y = 5.dp
                )
                .background(secondColor, shape = RoundedCornerShape(12.dp))
        )

        Box(
            modifier = Modifier
                .zIndex(2f)
                .size(width = 150.dp, height = 60.dp)
                .background(mainColor, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun DoubleColorButtonPreview() {
    DoubleColorButton(mainColor = Color.Red, secondColor = Color.Green) {
        Text(text = "Hello")
    }
}