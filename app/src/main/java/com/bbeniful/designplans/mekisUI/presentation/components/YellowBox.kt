package com.bbeniful.designplans.mekisUI.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bbeniful.designplans.core.ui.theme.orangeColor

@Composable
fun YellowBox(
    modifier: Modifier = Modifier,
    height: Dp = 300.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(orangeColor)
    ) {
        content()
    }
}