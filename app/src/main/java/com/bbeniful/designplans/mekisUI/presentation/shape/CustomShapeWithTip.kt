package com.bbeniful.designplans.mekisUI.presentation.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class CustomShapeWithTip(
    private val cornerRadius: Dp,
    private val tipWidth: Dp,
    private val tipHeight: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val tipWidthPx = with(density) { tipWidth.toPx() }
        val tipHeightPx = with(density) { tipHeight.toPx() }

        val path = Path().apply {
            // Top-left rounded corner
            moveTo(0f, cornerRadiusPx)
            arcTo(
                rect = androidx.compose.ui.geometry.Rect(0f, 0f, cornerRadiusPx * 2, cornerRadiusPx * 2),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Top-right rounded corner
            lineTo(size.width - cornerRadiusPx, 0f)
            arcTo(
                rect = androidx.compose.ui.geometry.Rect(
                    size.width - cornerRadiusPx * 2, 0f,
                    size.width, cornerRadiusPx * 2
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Bottom-right corner without rounding for the tip
            lineTo(size.width, size.height - tipHeightPx)

            // Draw the tip at the bottom-right
            lineTo(size.width - tipWidthPx / 2, size.height)
            lineTo(size.width - tipWidthPx, size.height - tipHeightPx)

            // Bottom-left rounded corner
            lineTo(cornerRadiusPx, size.height - tipHeightPx)
            arcTo(
                rect = androidx.compose.ui.geometry.Rect(
                    0f, size.height - cornerRadiusPx * 2 - tipHeightPx,
                    cornerRadiusPx * 2, size.height - tipHeightPx
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            close()
        }

        return Outline.Generic(path)
    }
}