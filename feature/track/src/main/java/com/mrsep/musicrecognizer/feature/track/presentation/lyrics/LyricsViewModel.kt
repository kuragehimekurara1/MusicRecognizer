package com.mrsep.musicrecognizer.feature.track.presentation.lyrics

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrsep.musicrecognizer.feature.track.domain.PreferencesRepository
import com.mrsep.musicrecognizer.feature.track.domain.TrackRepository
import com.mrsep.musicrecognizer.feature.track.domain.model.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LyricsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    trackRepository: TrackRepository,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val args = LyricsScreen.Args(savedStateHandle)

    val uiStateStream = trackRepository.getLyricsFlowById(args.mbId)
        .combine(preferencesRepository.userPreferencesFlow) { lyrics, preferences ->
            lyrics?.let {
                LyricsUiState.Success(
                    lyrics = lyrics,
                    fontStyle = preferences.lyricsFontStyle
                )
            } ?: LyricsUiState.LyricsNotFound
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LyricsUiState.Loading
        )

    fun setLyricsFontStyle(newStyle: UserPreferences.LyricsFontStyle) {
        viewModelScope.launch {
            preferencesRepository.setLyricsFontStyle(newStyle)
        }
    }

}

@Immutable
internal sealed class LyricsUiState {

    object Loading : LyricsUiState()

    object LyricsNotFound : LyricsUiState()

    data class Success(
        val lyrics: String,
        val fontStyle: UserPreferences.LyricsFontStyle
    ) : LyricsUiState()

}