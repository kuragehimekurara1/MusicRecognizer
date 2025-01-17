package com.mrsep.musicrecognizer.data.preferences

import com.mrsep.musicrecognizer.data.track.MusicServiceDo
import kotlinx.coroutines.flow.Flow

interface PreferencesRepositoryDo {

    val userPreferencesFlow: Flow<UserPreferencesDo>

    suspend fun setApiToken(value: String)
    suspend fun setOnboardingCompleted(value: Boolean)
    suspend fun setNotificationServiceEnabled(value: Boolean)
    suspend fun setDynamicColorsEnabled(value: Boolean)
    suspend fun setArtworkBasedThemeEnabled(value: Boolean)
    suspend fun setRequiredMusicServices(services: List<MusicServiceDo>)
    suspend fun setDeveloperModeEnabled(value: Boolean)
    suspend fun setFallbackPolicy(value: UserPreferencesDo.FallbackPolicyDo)
    suspend fun setLyricsFontStyle(value: UserPreferencesDo.LyricsFontStyleDo)
    suspend fun setTrackFilter(value: UserPreferencesDo.TrackFilterDo)
    suspend fun setHapticFeedback(value: UserPreferencesDo.HapticFeedbackDo)
    suspend fun setUseColumnForLibrary(value: Boolean)
    suspend fun setThemeMode(value: ThemeModeDo)
    suspend fun setUsePureBlackForDarkTheme(value: Boolean)

}