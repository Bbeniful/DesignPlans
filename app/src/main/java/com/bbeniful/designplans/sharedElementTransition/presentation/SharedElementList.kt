package com.bbeniful.designplans.sharedElementTransition.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

typealias imageURL = String

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SharedElementList(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (imageURL, String) -> Unit
) {

    val list = listOf(
        Items(
            imageURL = "https://picsum.photos/200/300",
            text = "helloka"
        ),
        Items(
            imageURL = "https://picsum.photos/200/300",
            text = "helloka"
        ),
        Items(
            imageURL = "https://picsum.photos/200/300",
            text = "helloka"
        ),
        Items(
            imageURL = "https://picsum.photos/200/300",
            text = "helloka"
        )
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp)) {
        LazyColumn {
            items(list) { item ->
                Row(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onItemClick(item.imageURL, item.text)
                    }) {
                    AsyncImage(
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "image/${item.imageURL}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                        model = imageURL, contentDescription = ""
                    )
                    Text(
                        text = item.text, modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "text/${item.text}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    )
                }
            }
        }
    }
}

data class Items(
    val imageURL: imageURL,
    val text: String
)