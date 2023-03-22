package com.mrsep.musicrecognizer.data.preferences

import com.mrsep.musicrecognizer.UserPreferencesProto
import com.mrsep.musicrecognizer.domain.model.Mapper
import com.mrsep.musicrecognizer.domain.model.UserPreferences
import javax.inject.Inject

class PreferencesToDomainMapper @Inject constructor() :
    Mapper<UserPreferencesProto, UserPreferences> {

    override fun map(input: UserPreferencesProto): UserPreferences {
        return UserPreferences(
            onboardingCompleted = input.onboardingCompleted,
            apiToken = input.apiToken,
            notificationServiceEnabled = input.notificationServiceEnabled,
            dynamicColorsEnabled = input.dynamicColorsEnabled,
            developerModeEnabled = input.developerModeEnabled,
            requiredServices = UserPreferences.RequiredServices(
                spotify = input.requiredServices.spotify,
                appleMusic = input.requiredServices.appleMusic,
                deezer = input.requiredServices.deezer,
                napster = input.requiredServices.napster,
                musicbrainz = input.requiredServices.musicbrainz
            )
        )
    }

}