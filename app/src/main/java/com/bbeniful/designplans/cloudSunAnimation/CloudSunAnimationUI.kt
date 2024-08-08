package com.bbeniful.designplans.cloudSunAnimation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bbeniful.designplans.R
import kotlinx.coroutines.delay

@Composable
fun CloudSunAnimationUI() {
    CloudSunAnimationContent()
}

const val TIME = 6000

@Composable
fun CloudSunAnimationContent() {




    var isAnimationStarted by remember {
        mutableStateOf(false)
    }

    val animateCloudOne = animateFloatAsState(
        targetValue = if (isAnimationStarted) 385f else -40f,
        label = "",
        animationSpec = tween(durationMillis = TIME, easing = LinearEasing)
    )

    val animateCloudTwo = animateFloatAsState(
        targetValue = if (isAnimationStarted) -12f else 385f,
        label = "",
        animationSpec = tween(durationMillis = TIME - 400, easing = LinearEasing)
    )

    val sunAnimation = animateFloatAsState(
        if (isAnimationStarted) -45f else 300f,
        label = "",
        animationSpec = tween(durationMillis = TIME + 500, easing = LinearOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        delay(1000)
        isAnimationStarted = true
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Blue)
        ) {

            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = density.run { animateCloudOne.value.dp.roundToPx() },
                            y = density.run { 55.dp.roundToPx() }
                        )
                    }
                    .align(Alignment.TopStart)
                    .size(50.dp, 50.dp)
                    .background(Color.White)
            )

            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = density.run { animateCloudTwo.value.dp.roundToPx() },
                            y = density.run { 95.dp.roundToPx() }
                        )
                    }
                    .zIndex(1f)
                    .align(Alignment.TopStart)
                    .size(50.dp, 50.dp)
                    .background(Color.White)
            )

            Image(
                modifier = Modifier.size(68.dp, 68.dp)
                    .offset {
                        IntOffset(
                            x = density.run {252.dp.roundToPx()  },
                            y = density.run { sunAnimation.value.dp.roundToPx() }
                        )
                    },
                painter = painterResource(id = R.drawable.img),
                contentDescription = ""
            )

        }
    }
}

@Preview
@Composable
fun CloudSunAnimationUIPreview() {
    CloudSunAnimationContent()
}
