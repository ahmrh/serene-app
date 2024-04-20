package com.ahmrh.serene.data.repository

import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ahmrh.serene.common.worker.SelfCareReminderWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkManagerRepository @Inject constructor(
    private val workManager: WorkManager
) {

    // TODO: Continue this
    
    fun scheduleReminder(
        duration: Long, unit: TimeUnit
    ){
        val data = Data.Builder()
        data.putString(SelfCareReminderWorker.nameKey, "Name")

        val workRequestBuilder = OneTimeWorkRequestBuilder<SelfCareReminderWorker>()
            .setInitialDelay(duration, unit)
            .setInputData(data.build())
            .build()

        workManager.enqueueUniqueWork(
            "Name" + duration,
            ExistingWorkPolicy.REPLACE,
            workRequestBuilder
        )
    }


    



    companion object{
        enum class SereneReminderType{
            TYPE_1,
            TYPE_2,
            TYPE_3,
        }
    }
}