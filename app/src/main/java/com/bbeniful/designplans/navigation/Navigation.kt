package com.bbeniful.designplans.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bbeniful.designplans.buttons.presentation.Buttons
import com.bbeniful.designplans.colorChange.presentation.ColorChangeUI
import com.bbeniful.designplans.designList.presentation.DesignListUI
import com.bbeniful.designplans.designList.utilities.Designs
import kotlinx.serialization.Serializable

@Composable
fun Navigation(paddingValues: PaddingValues) {
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
    }
}


@Serializable
object Home

@Serializable
object ColorChange

@Serializable
object Buttons