package com.viplearner.chorale.screens.chorale_view

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

object ChoraleNavigation {
    internal const val choraleEntityArg = "choraleEntityArg"
    const val route = "chorale"
    internal const val routeWithArgs = "$route/{$choraleEntityArg}"
}

fun NavGraphBuilder.choraleView(
    factory: ChoraleViewModel.Factory,
    onBackClick: () -> Unit
)= composable(
        route = ChoraleNavigation.routeWithArgs,
        arguments = listOf(
            navArgument(ChoraleNavigation.choraleEntityArg) {
                type = NavType.StringType
            }
        ),
        enterTransition = {
            slideInHorizontally(initialOffsetX = {it})
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = {it})
        }
    ) {
        it.arguments?.getString(ChoraleNavigation.choraleEntityArg)
        ?.let { choraleId ->
            ChoraleView(
                factory = factory,
                onBackClick = onBackClick,
                choraleId = choraleId
            )
        }
    }