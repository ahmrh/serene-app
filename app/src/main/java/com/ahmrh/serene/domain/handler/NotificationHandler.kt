package com.ahmrh.serene.domain.handler

import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ahmrh.serene.common.enums.Notification
import com.ahmrh.serene.common.worker.SelfCareReminderWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHandler @Inject constructor(
    private val workManager: WorkManager,
) {
    companion object {
        const val TAG = "NotificationHandler"
    }

    fun scheduleSelfCareReminderNotification(
    ) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<SelfCareReminderWorker>(
                repeatInterval = 1L, // there is a limit of 15 Minutes :(
                repeatIntervalTimeUnit = TimeUnit.DAYS
            )
                .setInitialDelay(1L, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork(
            "Self-care Reminder",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            periodicWorkRequest
        )

    }

    fun cancelNotificationReminder(notification: Notification) {
        workManager.cancelUniqueWork("$notification")
    }

    fun cancelAllNotification() = workManager.cancelAllWork()


}