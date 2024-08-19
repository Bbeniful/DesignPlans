package com.bbeniful.designplans.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bbeniful.designplans.animteLayoutChange.presentation.RowColumnToggle
import com.bbeniful.designplans.buttons.presentation.Buttons
import com.bbeniful.designplans.cloudSunAnimation.CloudSunAnimationUI
import com.bbeniful.designplans.colorChange.presentation.ColorChangeUI
import com.bbeniful.designplans.designList.presentation.DesignListUI
import com.bbeniful.designplans.gameUI.presentation.GameUI
import com.bbeniful.designplans.sharedElementTransition.presentation.SharedElementList
import com.bbeniful.designplans.sharedElementTransition.presentation.imageURL
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation(paddingValues: PaddingValues) {
    SharedTransitionLayout {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Home) {
            composable<Home> {
                DesignListUI { path ->
                    navController.navigate(path)
                }
            }
            composable<ColorChange> {
                ColorChangeUI(
                    padding = paddingValues
                )
            }

            composable<Buttons> {
                Buttons()
            }

            composable<SharedList> {
                SharedElementList(animatedVisibilityScope = this) { url, text ->
                    navController.navigate(SharedDetails(url, text))
                }
            }

            composable<SharedDetails> {
                val url = it.arguments?.getString("imageURL") as imageURL
                val text = it.arguments?.getString("data") ?: ""
                SharedDetails(url, text)
            }
            composable<AnimateLayoutChange> {
                RowColumnToggle()
            }
            composable<CloudSunAnimation> {
                CloudSunAnimationUI()
            }
            composable<GamingUINav> {
                GameUI()
            }

        }
    }

}


@Serializable
object Home

@Serializable
object ColorChange

@Serializable
object Buttons

@Serializable
object SharedList

@Serializable
object AnimateLayoutChange

@Serializable
object CloudSunAnimation

@Serializable
object GamingUINav

@Serializable
data class SharedDetails(
    val imageURL: imageURL,
    val data: String
)
