package com.viplearner.chorale.screens.chorale_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.viplearner.chorale.model.Song
import com.viplearner.chorale.model.SongDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChoraleViewModel @AssistedInject constructor(
    @Assisted("choraleId") private val choraleId: String,
): ViewModel() {

    val _viewState = MutableStateFlow<ChoraleViewState>(ChoraleViewState.Init)
    val viewState = _viewState.asStateFlow()

    init {
        _viewState.value = ChoraleViewState.Loading
        //load chorale
        loadChorale()
    }

    private fun loadChorale() = viewModelScope.launch(Dispatchers.IO){
        _viewState.value = ChoraleViewState.Loading
        //load chorale
        _viewState.value = ChoraleViewState.Success(
            SongDetails(
                songId = "1",
                song = "For the Lord God omnipotent reigneth\nThe kingdom of this world and of his Christ\nAnd so on and so forth\nAnother line in the lyrics\nAnd yet another",
                solfa = "d: r: m: d | l: t:-:- || d: r: m:\nd: r: m: d | l: t:-:- || d: r: m:\nd: r: m: d | l: t:-:- || d: r: m:\nd: r: m: d | l: t:-:- || d: r: m:\nd: r: m: d | l: t:-:- || d: r: m:"
            )
        )
    }
    sealed class ChoraleViewState {
        data object Init: ChoraleViewState()
        data object Loading: ChoraleViewState()
        data class Success(val songDetails: SongDetails): ChoraleViewState()
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("choraleId") choraleId: String,
        ): ChoraleViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            choraleId: String,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return assistedFactory.create(
                    choraleId,
                ) as T
            }
        }
    }
}