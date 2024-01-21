package com.viplearner.chorale.screens.song_list_view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viplearner.chorale.R
import com.viplearner.chorale.components.ErrorDialog
import com.viplearner.chorale.components.ProgressDialog
import com.viplearner.chorale.model.Song
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SongListView(
    onNavigateToSong: (String) -> Unit,
    viewModel: SongListViewModel = hiltViewModel()
){
    val viewState by viewModel.viewState.collectAsState()
    when(viewState){
        SongListViewModel.SongListViewState.Init -> {

        }
        SongListViewModel.SongListViewState.Loading -> {
            ProgressDialog()
        }
        is SongListViewModel.SongListViewState.Error -> {
            ErrorDialog(errorMessage = (viewState as SongListViewModel.SongListViewState.Error).message)
        }
        is SongListViewModel.SongListViewState.Success -> {
            SongListContent(
                songList = (viewState as SongListViewModel.SongListViewState.Success).songList,
                onNavigateToSong = onNavigateToSong
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListContent(
    songList: List<Song>,
    onNavigateToSong: (String) -> Unit
){
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    scrolledContainerColor = Color.Transparent
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null, tint = Color(0xFF8ea814))
                    }
                },
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        text = "Song List",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontFamily = FontFamily(
                                Font(
                                    resId = R.font.raleway_regular,
                                    style = FontStyle.Italic,
                                )
                            )
                        ),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8ea814)
                    )
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier
            .padding(it)
            .padding(horizontal = 20.dp)) {
            items(songList){song ->
                SongListItem(
                    modifier = Modifier.padding(vertical = 15.dp), song = song, onClick = {
                        onNavigateToSong(
                            song.id
                        )
                    }
                )
                Divider(color = Color.Gray.copy(alpha = 0.5f))
            }
        }
    }
}

@Composable
fun SongListItem(
    modifier: Modifier = Modifier,
    song: Song,
    onClick: () -> Unit
){
    Surface(
        modifier = modifier,
        onClick = onClick,
        color = Color.Black
    ) {
        Row {
            Column(modifier = Modifier) {
                Text(
                    text = song.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily(
                            Font(
                                resId = R.font.raleway_regular,
                                style = FontStyle.Italic,
                            )
                        )
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = song.vocalTypes,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily(
                            Font(
                                resId = R.font.raleway_regular,
                                style = FontStyle.Italic,
                            )
                        )
                    ),
                    fontWeight = FontWeight.Thin,
                    color = Color.White
                )
                Text(
                    text = song.formattedDate,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily(
                            Font(
                                resId = R.font.raleway_regular,
                                style = FontStyle.Italic,
                            )
                        )
                    ),
                    fontWeight = FontWeight.Thin,
                    color = Color.White
                )
            }
            IconButton(onClick = onClick) {
                Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
fun SongListPreview(){
    SongListContent(songList = listOf(
        Song("1","Song 1", "2021-10-10", "Soprano"),
        Song("2","Song 2", "2021-10-10", "Soprano"),
        Song("2","Song 3", "2021-10-10", "Soprano"),
        Song("2","Song 4", "2021-10-10", "Soprano"),
        Song("2","Song 5", "2021-10-10", "Soprano"),
        Song("2","Song 6", "2021-10-10", "Soprano"),
        Song("2","Song 7", "2021-10-10", "Soprano"),
        Song("2","Song 8", "2021-10-10", "Soprano"),
        Song("2","Song 9", "2021-10-10", "Soprano"),
        Song("2","Song 10", "2021-10-10", "Soprano"),
        Song("2","Song 11", "2021-10-10", "Soprano"),
        Song("2","Song 12", "2021-10-10", "Soprano"),
        Song("2","Song 13", "2021-10-10", "Soprano"),
        Song("2","Song 14", "2021-10-10", "Soprano"),
        Song("2","Song 15", "2021-10-10", "Soprano"),
        Song("2","Song 16", "2021-10-10", "Soprano"),
        Song("2","Song 17", "2021-10-10", "Soprano"),
        Song("2","Song 18", "2021-10-10", "Soprano"),
        Song("2","Song 19", "2021-10-10", "Soprano"),
        Song("2","Song 20", "2021-10-10", "Soprano"),
        Song("2","Song 21", "2021-10-10", "Soprano"),
        Song("2","Song 22", "2021-10-10", "Soprano"),
        Song("2","Song 23", "2021-10-10", "Soprano"),
        Song("2","Song 24", "2021-10-10", "Soprano"),
        Song("2","Song 25", "2021-10-10", "Soprano")
    ),
        onNavigateToSong = {}
    )
}