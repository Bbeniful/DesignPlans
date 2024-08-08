package com.bbeniful.designplans.sharedElementTransition

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    imageURL: String,
    text: String
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.sharedElement(
                state = rememberSharedContentState(key = "image/$imageURL"),
                animatedVisibilityScope = animatedVisibilityScope
            ),
            model = imageURL, contentDescription = ""
        )
    }
}