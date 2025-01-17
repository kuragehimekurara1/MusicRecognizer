package com.mrsep.musicrecognizer

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.mrsep.musicrecognizer.feature.recognition.presentation.service.NotificationService
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import javax.inject.Inject

@HiltAndroidApp
class MusicRecognizerApp : Application(), ImageLoaderFactory, Configuration.Provider {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        NotificationService.createStatusNotificationChannel(this)
        NotificationService.createResultNotificationChannel(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .okHttpClient { okHttpClient }
            .crossfade(true)
            .respectCacheHeaders(false)
            .build()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}