package com.mrsep.musicrecognizer.data.recorder

import android.content.Context
import android.widget.Toast
import com.mrsep.musicrecognizer.R
import com.mrsep.musicrecognizer.di.ApplicationScope
import com.mrsep.musicrecognizer.di.IoDispatcher
import com.mrsep.musicrecognizer.di.MainDispatcher
import com.mrsep.musicrecognizer.domain.FileRecordRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import java.io.File
import javax.inject.Inject

class FileRecordRepositoryImpl @Inject constructor(
    @ApplicationContext private val appContext: Context,
    @ApplicationScope private val appScope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : FileRecordRepository {

    private val recordsDirPath = "${appContext.filesDir.absolutePath}/records"

    init {
        File(recordsDirPath).run { if (!exists()) mkdir() }
    }

    override fun getFileForNewRecord(extension: String) =
        File("$recordsDirPath/rec_${System.currentTimeMillis()}.$extension")

    override fun delete(recordFile: File) {
        appScope.launch(ioDispatcher) {
            var numTries = 0
            val maxTries = 3
            while (numTries < maxTries) {
                try {
                    if (recordFile.exists() && recordFile.delete()) {
                        return@launch
                    }
                    numTries++
                    delay(1000L)
                } catch (e: Exception) {
                    showErrorToast(appContext.getString(R.string.record_deletion_failed))
                    e.printStackTrace()
                }
            }
        }
    }

    private suspend fun showErrorToast(message: String) {
        withContext(mainDispatcher) {
            Toast.makeText(appContext, message, Toast.LENGTH_LONG).show()
        }
    }

}