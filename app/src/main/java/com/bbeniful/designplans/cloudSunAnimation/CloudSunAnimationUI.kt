package com.bbeniful.designplans.cloudSunAnimation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import androidx.compose.ui.zIndex
import com.bbeniful.designplans.R
import com.bbeniful.designplans.core.ui.theme.darkSky
import com.bbeniful.designplans.core.ui.theme.lightSky
import kotlinx.coroutines.delay

@Composable
fun CloudSunAnimationUI() {
    CloudSunAnimationContent()
}

const val TIME = 6000

@Composable
fun CloudSunAnimationContent() {

    val screenHeight = LocalConfiguration.current.screenHeightDp
    var isAnimationStarted by remember {
        mutableStateOf(false)
    }

    val animateCloudOne = animateFloatAsState(
        targetValue = if (isAnimationStarted) 385f else -80f,
        label = "",
        animationSpec = tween(durationMillis = TIME + 100, easing = LinearOutSlowInEasing)
    )

    val animateCloudTwo = animateFloatAsState(
        targetValue = if (isAnimationStarted) -565f else 85f,
        label = "",
        animationSpec = tween(
            durationMillis = TIME + 400,
            easing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)
        )
    )

    val animateCloudThree = animateFloatAsState(
        targetValue = if (isAnimationStarted) -965f else 355f,
        label = "",
        animationSpec = tween(
            durationMillis = TIME + 2000,
            easing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)
        )
    )

    val sunAnimation = animateDpAsState(
        if (isAnimationStarted) -(screenHeight - 600).dp else 495.dp,
        label = "",
        animationSpec = tween(
            durationMillis = TIME + 2500,
            easing = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1f)
        )
    )

    val hideCloudAlpha = animateFloatAsState(
        targetValue = if (isAnimationStarted) 0.2f else 1f,
        label = "",
        animationSpec = tween(
            durationMillis = TIME + 1500,
            easing = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1f)
        )
    )

    LaunchedEffect(Unit) {
        delay(1000)
        isAnimationStarted = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        darkSky, lightSky
                    )
                )
            ), verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                modifier = Modifier
                    .testTag("sun")
                    .size(328.dp, 328.dp)
                    .offset {
                        IntOffset(
                            x = density.run { 202.dp.roundToPx() },
                            y = density.run { sunAnimation.value.roundToPx() }
                        )
                    },
                painter = painterResource(id = R.drawable.sun),
                contentDescription = ""
            )
            Image(
                modifier = Modifier
                    .testTag("cloud1")
                    .size(228.dp, 228.dp)
                    .offset {
                        IntOffset(
                            x = density.run { animateCloudOne.value.dp.roundToPx() },
                            y = density.run { 55.dp.roundToPx() }
                        )
                    }

                    .align(Alignment.TopStart)
                    .alpha(hideCloudAlpha.value),
                painter = painterResource(id = R.drawable.cloude),
                contentDescription = ""
            )
            Image(
                modifier = Modifier
                    .testTag("cloud2")
                    .size(228.dp, 228.dp)
                    .offset {
                        IntOffset(
                            x = density.run { animateCloudTwo.value.dp.roundToPx() },
                            y = density.run { 95.dp.roundToPx() }
                        )
                    }
                    .align(Alignment.TopEnd)
                    .alpha(hideCloudAlpha.value),
                painter = painterResource(id = R.drawable.cloude),
                contentDescription = ""
            )

            Image(
                modifier = Modifier
                    .testTag("cloud3")
                    .size(428.dp, 428.dp)
                    .offset {
                        IntOffset(
                            x = density.run { animateCloudThree.value.dp.roundToPx() },
                            y = density.run { 195.dp.roundToPx() }
                        )
                    }
                    .align(Alignment.TopEnd)
                    .alpha(hideCloudAlpha.value),
                painter = painterResource(id = R.drawable.cloude),
                contentDescription = ""
            )

        }
        Image(
            painter = painterResource(id = R.drawable.montain),
            contentDescription = ""
        )

    }
}

@Preview
@Composable
fun CloudSunAnimationUIPreview() {
    CloudSunAnimationContent()
}
