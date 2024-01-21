package com.viplearner.chorale.navigation

import androidx.annotation.NavigationRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.viplearner.chorale.screens.chorale_view.ChoraleNavigation
import com.viplearner.chorale.screens.chorale_view.ChoraleViewModel
import com.viplearner.chorale.screens.chorale_view.choraleView
import com.viplearner.chorale.screens.song_list_view.SongListNavigation
import com.viplearner.chorale.screens.song_list_view.songListView

@Composable
fun Navigation(
    navController: NavHostController,
    choraleViewModelFactory: ChoraleViewModel.Factory
){
    NavHost(
        navController = navController,
        startDestination = SongListNavigation.route
    ){
        choraleView(choraleViewModelFactory){
            navController.popBackStack()
        }
        songListView(
            onNavigateToSong = { songId ->
                navController.navigate("${ChoraleNavigation.route}/$songId")
            }
        )
    }
}