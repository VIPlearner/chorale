package com.viplearner.chorale.screens.song_list_view

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object SongListNavigation {
    const val route = "songList"
}

fun NavGraphBuilder.songListView(
    onNavigateToSong: (String) -> Unit
){
    composable(SongListNavigation.route) {
        SongListView(
            onNavigateToSong = onNavigateToSong
        )
    }
}