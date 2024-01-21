package com.viplearner.chorale.screens.song_list_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viplearner.chorale.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SongListViewModel: ViewModel() {
    private val _viewState = MutableStateFlow<SongListViewState>(SongListViewState.Init)
    val viewState = _viewState.asStateFlow()

    init {
        loadSongList()
    }

    fun loadSongList() = viewModelScope.launch(Dispatchers.IO) {
        _viewState.value = SongListViewState.Loading
        //load Song List
        val songList = listOf(
            Song("2","Hallelujah Chorus", "2nd March, 2024", "Soprano, Alto, Tenor, Bass"),
            Song("2","Hallelujah Chorus", "2nd March, 2024", "Soprano, Alto, Tenor, Bass"),
            Song("2","Hallelujah Chorus", "2nd March, 2024", "Soprano, Alto, Tenor, Bass"),
            Song("2","Hallelujah Chorus", "2nd March, 2024", "Soprano, Alto, Tenor, Bass"),
            Song("2","Hallelujah Chorus", "2nd March, 2024", "Soprano, Alto, Tenor, Bass"),
            Song("2","Hallelujah Chorus", "2nd March, 2024", "Soprano, Alto, Tenor, Bass"),
            Song("2","Hallelujah Chorus", "2nd March, 2024", "Soprano, Alto, Tenor, Bass"),
            Song("2","Hallelujah Chorus", "2nd March, 2024", "Soprano, Alto, Tenor, Bass"),
        )
        _viewState.value = SongListViewState.Success(songList)
    }


    sealed class SongListViewState {
        data object Init: SongListViewState()
        data object Loading: SongListViewState()
        data class Error(val message: String): SongListViewState()
        data class Success(val songList: List<Song>): SongListViewState()
    }
}