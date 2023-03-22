package com.mrsep.musicrecognizer.domain.model

data class UserPreferences(
    val onboardingCompleted: Boolean,
    val apiToken: String,
    val requiredServices: RequiredServices,
    val notificationServiceEnabled: Boolean,
    val dynamicColorsEnabled: Boolean,
    val developerModeEnabled: Boolean,
) {

    data class RequiredServices(
        val spotify: Boolean,
        val appleMusic: Boolean,
        val deezer: Boolean,
        val napster: Boolean,
        val musicbrainz: Boolean
    )

}

// save as list as a variant
//enum class MusicService {
//    SPOTIFY, APPLE_MUSIC, DEEZER, NAPSTER, MUSICBRAINZ
//}