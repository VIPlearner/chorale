package com.viplearner.chorale.navigation

import androidx.annotation.NavigationRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.viplearner.chorale.screens.chorale_view.ChoraleViewModel
import com.viplearner.chorale.screens.chorale_view.choraleView

@Composable
fun Navigation(
    navController: NavHostController,
    choraleViewModelFactory: ChoraleViewModel.Factory
){
    NavHost(
        navController = navController,
        startDestination = "HomeNavigation.route"
    ){
        choraleView(choraleViewModelFactory){
            navController.popBackStack()
        }



    }
}