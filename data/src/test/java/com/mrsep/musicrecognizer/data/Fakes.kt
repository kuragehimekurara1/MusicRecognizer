package com.mrsep.musicrecognizer.data

import com.mrsep.musicrecognizer.data.track.TrackEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

internal val fakeTrack = TrackEntity(
    mbId = UUID.randomUUID().toString(),
    title = "Echoes",
    artist = "Pink Floyd",
    album = "Meddle",
    releaseDate = LocalDate.parse("1971-10-30", DateTimeFormatter.ISO_DATE),
    lyrics = "lyrics stub",
    links = TrackEntity.Links(
        artwork = "https://upload.wikimedia.org/wikipedia/ru/1/1e/Meddle_album_cover.jpg",
        spotify = null,
        youtube = null,
        soundCloud = null,
        appleMusic = null,
        musicBrainz = null,
        deezer = null,
        napster = null
    ),
    metadata = TrackEntity.Metadata(
        lastRecognitionDate = Instant.now(),
        isFavorite = false
    )
)

internal fun emptyAudioRecordingFlow(delayBeforeClose: Long) = flow<ByteArray> {
    delay(delayBeforeClose)
}

internal val normalAudioFlowDuration = 5000L
internal val normalAudioDelay1 = 0L
internal val normalAudioDelay2 = 1000L
internal val normalAudioDelay3 = 1500L
internal val normalAudioDelay4 = 1000L
internal val normalAudioDelay5 = 1500L
internal val normalAudioRecordingFlow get() = flow {
    delay(normalAudioDelay1)
    emit(ByteArray(100 * 1024))
    println("Emitted rec #1")
    delay(normalAudioDelay2)
    emit(ByteArray(200 * 1024))
    println("Emitted rec #2")
    delay(normalAudioDelay3)
    emit(ByteArray(300 * 1024))
    println("Emitted rec #3")
    delay(normalAudioDelay4)
    emit(ByteArray(400 * 1024))
    println("Emitted rec #4")
    delay(normalAudioDelay5)
    emit(ByteArray(500 * 1024))
    println("Emitted rec #5")
}