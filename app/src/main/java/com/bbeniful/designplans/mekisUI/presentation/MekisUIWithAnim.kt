package com.bbeniful.designplans.mekisUI.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.designplans.R
import com.bbeniful.designplans.core.ui.theme.dirtyWhite
import com.bbeniful.designplans.mekisUI.presentation.components.ButtonsWithIcon
import com.bbeniful.designplans.mekisUI.presentation.components.YellowBox

@Composable
fun MekisUIWithAnim() {
    MekisUIWithAnimContent()
}

@Composable
fun MekisUIWithAnimContent() {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    //var boxHeight =



    val positionVertical by animateAlignmentAsState(if (!isExpanded) Alignment.Center else Alignment.TopStart)

    var heightOfBox by remember {
        mutableStateOf(0.dp)
    }

    val yellowBoxHeight by
    animateDpAsState(
        targetValue = if (isExpanded) heightOfBox + 300.dp else 300.dp,
        animationSpec = tween(1000),
        label = ""
    )
    

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dirtyWhite)
            .onGloballyPositioned {
                heightOfBox = it.size.height.dp
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            YellowBox(
                height = yellowBoxHeight
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = positionVertical
                ) {
                    this@ElevatedCard.AnimatedVisibility(visible = isExpanded) {
                        Image(
                            modifier = Modifier.size(150.dp),
                            painter = painterResource(R.drawable.example_qr),
                            contentDescription = ""
                        )
                    }
                }
            }

        }
    }

}

@Composable
fun HeaderItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
    ) {
        Text(text = "almafa")
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black

        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "almafa")
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black

        )
    }
}

@Preview
@Composable
fun PreviewMekisUIWithAnim() {
    MekisUIWithAnimContent()
}


@Composable
fun animateAlignmentAsState(
    targetAlignment: Alignment,
): State<Alignment> {
    val biased = targetAlignment as BiasAlignment
    val horizontal by animateFloatAsState(
        biased.horizontalBias, label = "", animationSpec = tween(500)
    )
    val vertical by animateFloatAsState(biased.verticalBias, label = "", animationSpec = tween(500))
    return remember { derivedStateOf { BiasAlignment(horizontal, vertical) } }
}

@Composable
fun animateAlignmentHorizontalAsState(
    targetAlignment: Alignment,
): State<Alignment.Horizontal> {
    val biased = targetAlignment as BiasAlignment
    val horizontal by animateFloatAsState(
        biased.horizontalBias, label = "", animationSpec = tween(500)
    )
    val vertical by animateFloatAsState(biased.verticalBias, label = "", animationSpec = tween(500))
    return remember { derivedStateOf { BiasAlignment.Horizontal(horizontal) } }
}

@Composable
fun animateVerticalArrangementAsState(
    targetArrangement: Float
): State<Arrangement.Vertical> {
    val animatedBias by animateFloatAsState(
        targetValue = targetArrangement,
        label = "",
        animationSpec = tween(500)
    )

    return remember {
        derivedStateOf {
            when {
                animatedBias <= -0.5f -> Arrangement.Top
                animatedBias >= 0.5f -> Arrangement.Bottom
                else -> Arrangement.Center
            }
        }
    }
}

@Composable
fun animateVerticalArrangementAsStateTwo(
    targetArrangement: Float
): State<Arrangement.Vertical> {
    val animatedBias by animateFloatAsState(
        targetValue = targetArrangement,
        label = "",
        animationSpec = tween(500)
    )

    return remember {
        derivedStateOf {
            when {
                animatedBias <= -0.5f -> Arrangement.Top
                animatedBias >= 0.5f -> Arrangement.Bottom
                else -> Arrangement.Center
            }
        }
    }
}