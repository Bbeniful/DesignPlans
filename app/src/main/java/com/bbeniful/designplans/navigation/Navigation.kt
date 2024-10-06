package com.bbeniful.designplans.navigation

import android.widget.Toast
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bbeniful.designplans.animatedColorBg.presentation.AnimatedColorBackgroundUI
import com.bbeniful.designplans.animteLayoutChange.presentation.RowColumnToggle
import com.bbeniful.designplans.buttons.presentation.Buttons
import com.bbeniful.designplans.calendar.presentation.CalendarUI
import com.bbeniful.designplans.clickableText.presentation.ClickableTextUI
import com.bbeniful.designplans.cloudSunAnimation.CloudSunAnimationUI
import com.bbeniful.designplans.colorChange.presentation.ColorChangeUI
import com.bbeniful.designplans.designList.presentation.DesignListUI
import com.bbeniful.designplans.gameUI.presentation.GameUI
import com.bbeniful.designplans.mekisUI.presentation.MekisUI
import com.bbeniful.designplans.mekisUI.presentation.MekisUIWithAnim
import com.bbeniful.designplans.navigationDrawer.NavigationDrawer
import com.bbeniful.designplans.navigationDrawer.News
import com.bbeniful.designplans.navigationDrawer.SingleNews
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
            composable<Calendar> {
                CalendarUI(
                    modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
                )
            }
            composable<ClickTextNav> {
                val context = LocalContext.current
                ClickableTextUI(
                    onTextClick = { navController.popBackStack() },
                    onTextClickSecond = {
                        Toast.makeText(context, "Second Click", Toast.LENGTH_SHORT).show()
                    },
                    onClickWithText = { text ->
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                    }
                )
            }
            composable<AnimatedColorBGNav> {
                AnimatedColorBackgroundUI()
            }

            composable<MekisUINav> {
                MekisUI {
                    navController.navigate(MekisUIWithAnimNav)
                }
            }
            composable<MekisUIWithAnimNav> {
                MekisUIWithAnim()
            }
            composable<NavigationDrawer> {
                NavigationDrawer(
                    paddingValues = paddingValues
                ) { news ->
                    navController.navigate(SingleNewsRoute(news.title))
                }
            }

            composable<SingleNewsRoute> {
                val news = it.toRoute<SingleNewsRoute>()
                SingleNews(
                    paddingValues = paddingValues,
                    title = news.title
                )
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
object Calendar

@Serializable
object ClickTextNav

@Serializable
object AnimatedColorBGNav

@Serializable
object MekisUINav

@Serializable
object MekisUIWithAnimNav

@Serializable
object NavigationDrawer

@Serializable
data class SingleNewsRoute(
    val title: String
)

@Serializable
data class SharedDetails(
    val imageURL: imageURL,
    val data: String
)
