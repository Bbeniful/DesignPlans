package com.bbeniful.designplans.colorChange.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bbeniful.designplans.core.ui.theme.lightBlue
import com.bbeniful.designplans.core.ui.theme.lightGreen
import com.bbeniful.designplans.core.ui.theme.lightOrange
import com.bbeniful.designplans.core.ui.theme.lightPink
import com.bbeniful.designplans.core.ui.theme.lightPurple
import com.bbeniful.designplans.core.ui.theme.lightYellow

@Composable
fun ColorChangeUI(padding: PaddingValues) {
    ColorChangeContent(
        padding = padding,
        colors = listOf(
            lightPink,
            lightBlue,
            lightPurple,
            lightGreen,
            lightOrange,
            lightYellow
        )
    )
}

@Composable
fun ColorChangeContent(padding: PaddingValues, colors: List<Color> = emptyList()) {

    var previousColor by remember { mutableStateOf(lightBlue) }
    var newColor by remember { mutableStateOf(lightBlue) }
    var isClicked by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isClicked) newColor else previousColor, label = ""
    )

    var shouldShowColors by remember { mutableStateOf(false) }

    var expandedItemIndex by remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = padding.calculateTopPadding())
            .background(backgroundColor)
    ) {
        AnimatedVisibility(
            visible = shouldShowColors,
            enter = scaleIn(
                animationSpec = tween(400), transformOrigin = TransformOrigin(
                    pivotFractionX = 1f,
                    pivotFractionY = 0.5f
                )
            ),
            exit = scaleOut(
                animationSpec = tween(500),
                transformOrigin = TransformOrigin(
                    pivotFractionX = 1f,
                    pivotFractionY = 0.5f
                )
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    itemsIndexed(colors) { index, item ->
                        ColorsItem(
                            color = item,
                            isExpanded = index == expandedItemIndex,
                            onSlideClick = {
                                expandedItemIndex = if (expandedItemIndex == index) -1 else index
                            }
                        ) { selectedColor ->
                            expandedItemIndex = if (expandedItemIndex == index) -1 else index
                            previousColor = item
                            isClicked = !isClicked
                            newColor = selectedColor
                        }
                    }
                }
            }

        }

        FloatingActionButton(
            onClick = {
                shouldShowColors = !shouldShowColors
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 15.dp, end = 15.dp)
        ) {
            Text(text = if (shouldShowColors) "X" else "Show colors")
        }

    }
}

@Composable
fun ColorsItem(
    color: Color,
    isExpanded: Boolean,
    onSlideClick: () -> Unit,
    onColorSelected: (Color) -> Unit
) {

    val width by animateFloatAsState(targetValue = if (isExpanded) 0.8f else 0.4f, label = "")
    val borderWidth by animateDpAsState(targetValue = if (isExpanded) 0.dp else 2.dp, label = "")

    val interactionSource = remember { MutableInteractionSource() }


    Row(
        modifier = Modifier
            .fillMaxWidth(width)
            .padding(top = 5.dp, bottom = 5.dp)
            .background(
                color = color, shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
            )
            .border(
                width = borderWidth,
                color = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
            )
            .height(80.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onSlideClick()
            },
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(visible = isExpanded) {
            Button(onClick = {
                onColorSelected(color)
            }) {
                Text(text = "Select")
            }
        }
    }
}


@Composable
fun ExpandableList() {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
    var expandedItemIndex by remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            itemsIndexed(items) { index, item ->
                ExpandableListItem(
                    item = item,
                    expanded = index == expandedItemIndex,
                    onClick = {
                        expandedItemIndex = if (expandedItemIndex == index) -1 else index
                    }
                )
            }
        }
    }
}

@Composable
fun ExpandableListItem(item: String, expanded: Boolean, onClick: () -> Unit) {

    val width by animateFloatAsState(targetValue = if (expanded) 1f else 0.4f, label = "")

    Row(
        modifier = Modifier
            .fillMaxWidth(width)
            .padding(8.dp)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = item,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ColorsItemPreview() {
    ColorChangeContent(
        PaddingValues(),
        colors = listOf(
            Color.Red, Color.Blue, Color.Green, Color.Yellow
        )
    )

}
