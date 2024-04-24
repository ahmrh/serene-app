package com.ahmrh.serene.domain.handler

import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ahmrh.serene.common.enums.Notification
import com.ahmrh.serene.common.worker.SelfCareReminderWorker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHandler @Inject constructor(
    private val workManager: WorkManager
) {

    // TODO: Continue this
    
     fun scheduleNotificationReminder(
        notification: Notification
    ){

        val data = Data.Builder()
        data.putInt(SelfCareReminderWorker.NOTIFICATION_ID_KEY, notification.id)

        val workRequestBuilder = OneTimeWorkRequestBuilder<SelfCareReminderWorker>()
            .setInitialDelay(notification.time, notification.timeUnit)
            .setInputData(data.build())
            .build()

        workManager.enqueueUniqueWork(
            "$notification" ,
            ExistingWorkPolicy.REPLACE,
            workRequestBuilder
        )
    }

    fun cancelNotificationReminder(notification: Notification){
        workManager.cancelUniqueWork("$notification" ,)
    }

    fun cancelAllNotification() = workManager.cancelAllWork()
    


}