package com.ahmrh.serene.common.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ahmrh.serene.common.enums.Notification
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.common.utils.makeSelfCareReminderNotification
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.handler.NotificationHandler
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import java.util.Date


class SelfCareReminderWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    val userRepository = UserRepository(
        auth = Firebase.auth, storage = Firebase.storage,
        firestore = Firebase.firestore
    )

    // continue this
    override suspend fun doWork(): Result {
        Log.d(TAG, "It seems the worker is working like the worker should be")
//        val comparedDate = userRepository.getLatestPracticeDate()
//            ?: userRepository.getAccountCreatedDate()
//
//        if (comparedDate == null) {
//            Log.e(
//                TAG,
//                "Compared date is null, whathu gonna do"
//            )
//            return Result.failure()
//        } else {
//            Log.d(TAG, "compared date hahah is $comparedDate")
//        }
//
//
//        return try {
//            val today = Date()
//
//            val millisecondsPerDay = (24 * 60 * 60 * 1000).toLong()
//            val yesterday = Date(today.time - millisecondsPerDay)
//            val twoDaysAgo = Date(yesterday.time - millisecondsPerDay)
//            val sevenDaysAgo = Date(today.time - 7 * millisecondsPerDay)
//            val tenDaysAgo = Date(today.time - 10 * millisecondsPerDay)
//            val fourteenDaysAgo =
//                Date(today.time - 14 * millisecondsPerDay)
//
//            val daysDifference =
//                DateUtils.daysBetween(Date(), fourteenDaysAgo)
//
//            val notification = Notification.fromDays(daysDifference)
//
//            makeSelfCareReminderNotification(
//                context,
//                notification
//            )
//
//            Result.success()
//
//        } catch (e: NoSuchElementException) {
//            Log.e(TAG, "it seems there won't be notification on that day")
//
//            Result.failure()
//        }

        val comparedDate = userRepository.getLatestPracticeDate()
            ?: userRepository.getAccountCreatedDate()

        if (comparedDate == null) {
            Log.e(
                TAG,
                "Compared date is null, whathu gonna do"
            )
            return Result.failure()
        } else {
            Log.e(
                TAG, "Compared date is not null hahah $comparedDate"
            )
        }

        val daysDifference =
            DateUtils.daysBetween(Date(), comparedDate)

        return try {

            val notification = Notification.fromDays(daysDifference)

            makeSelfCareReminderNotification(
                context,
                notification
            )

            Result.success()

        } catch (e: Exception) {
            Log.e(TAG, "it seems there won't be notification on that day $daysDifference")

            Result.failure()
        }

    }


    companion object {
        const val TAG = "SelfCareReminderWorker"
//        const val NOTIFICATION_ID_KEY = "NOTIFICATION_ID"

        const val DAYS_KEY = "DAYS_KEY"
    }


}