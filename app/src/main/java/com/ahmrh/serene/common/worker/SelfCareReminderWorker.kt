package com.ahmrh.serene.common.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ahmrh.serene.common.utils.makeReminderNotification

class SelfCareReminderWorker(
    context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {


        makeReminderNotification(
            "It's time to practice Self-care",
            applicationContext
        )

        return Result.success()
    }


    companion object {
        const val nameKey = "NAME"
    }
}