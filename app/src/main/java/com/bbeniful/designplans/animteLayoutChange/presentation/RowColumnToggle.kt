package com.bbeniful.designplans.animteLayoutChange.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RowColumnToggle() {
    var isRow by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Button(onClick = { isRow = !isRow }) {
            Text(text = if (isRow) "Switch to Column" else "Switch to Row")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Crossfade(targetState = isRow, label = "") { currentState ->
            if (currentState) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SampleItems()
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SampleItems()
                }
            }
        }
    }
}

@Composable
fun SampleItems() {
    for (i in 1..3) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .padding(4.dp)
        ) {
            Text(text = "Item $i")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowColumnTogglePreview() {
    RowColumnToggle()

}