package com.c137.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.c137.feature.character.CharacterGrid

@Composable
fun NavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Landing.route) {
        composable(Screen.Landing.route) {
            LandingPage {
                navController.navigate(Screen.CharacterGrid.route) {
                    popUpTo(Screen.Landing.route) { inclusive = true }
                }
            }
        }
        composable(Screen.CharacterGrid.route) {
            CharacterGrid()
        }
    }
}

sealed class Screen(val route: String) {

    object Landing : Screen("landing")
    object CharacterGrid : Screen("character_grid")
}