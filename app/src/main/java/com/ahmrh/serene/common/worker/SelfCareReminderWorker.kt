package com.ahmrh.serene.common.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ahmrh.serene.common.enums.Notification
import com.ahmrh.serene.common.utils.makeSelfCareReminderNotification

class SelfCareReminderWorker(
    private val context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {


    override suspend fun doWork(): Result {

        val notificationId = inputData.getInt(NOTIFICATION_ID_KEY, 0)

        val notification = Notification.fromId(notificationId)

        makeSelfCareReminderNotification(
            context,
            notification

        )

        return Result.success()
    }

    companion object{
        const val NOTIFICATION_ID_KEY = "NOTIFICATION_ID"
    }


}