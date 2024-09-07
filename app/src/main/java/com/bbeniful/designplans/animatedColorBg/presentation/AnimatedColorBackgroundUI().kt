package com.bbeniful.designplans.animatedColorBg.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.bbeniful.designplans.core.ui.theme.darkGreen
import com.bbeniful.designplans.core.ui.theme.digitalGreen

@Composable
fun AnimatedColorBackgroundUI() {
    val colors = listOf(
        digitalGreen,
        darkGreen
    )
    AnimatedColorBackgroundContent(
        colors = colors
    )
}

@Composable
fun AnimatedColorBackgroundContent(colors: List<Color> = emptyList()) {
    val infiniteTransition = rememberInfiniteTransition(label = "background")

    val targetOffset = with(LocalDensity.current) {
        1000.dp.toPx()
    }

    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetOffset,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(40.dp)
            .drawWithCache {
                val brushSize = 1000f
                val brush = Brush.linearGradient(
                    colors = colors,
                    start = Offset(offset,offset),
                    end = Offset(offset + brushSize, offset + brushSize),
                    tileMode = TileMode.Mirror
                )
                onDrawBehind { drawRect(brush) }
            }
    )
}
