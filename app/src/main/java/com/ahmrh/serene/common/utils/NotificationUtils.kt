package com.ahmrh.serene.common.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ahmrh.serene.MainActivity
import com.ahmrh.serene.R
import com.ahmrh.serene.common.CHANNEL_ID
import com.ahmrh.serene.common.NOTIFICATION_ID
import com.ahmrh.serene.common.REQUEST_CODE
import com.ahmrh.serene.common.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import com.ahmrh.serene.common.VERBOSE_NOTIFICATION_CHANNEL_NAME
import com.ahmrh.serene.common.enums.Notification


fun makeSelfCareReminderNotification(
    context: Context,
    notification: Notification,
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and no t in the support library
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(
            CHANNEL_ID,
            VERBOSE_NOTIFICATION_CHANNEL_NAME,

            importance

        )
        channel.description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION

        val notificationManager =
            context.getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    val pendingIntent: PendingIntent = createPendingIntent(context)

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(notification.title)
        .setContentText(notification.message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    if (ActivityCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return
    }
    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
}

private fun createPendingIntent(
    context: Context
): PendingIntent {
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    // Flag to detect unsafe launches of intents for Android 12 and higher
    // to improve platform security
    var flags = PendingIntent.FLAG_UPDATE_CURRENT
    flags = flags or PendingIntent.FLAG_IMMUTABLE

    return PendingIntent.getActivity(
        context,
        REQUEST_CODE,
        intent,
        flags
    )
}