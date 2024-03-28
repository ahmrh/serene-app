package com.ahmrh.serene.common.utils

import android.util.Log
import java.util.Calendar
import java.util.Date
import kotlin.math.abs

object DateUtils {
    const val TAG = "DateUtils"
    fun getDayStreak(dates: List<Date>): Int {
        if (dates.isEmpty()) {
            return 0
        }

        val sortedDates = dates.sortedBy { it.time }

        var currentStreak = 1
        var previousDate = sortedDates[0]

        for (date in sortedDates.subList(1, sortedDates.size)) {
            val daysDifference = daysBetween(previousDate, date)
            if (daysDifference == 1L) {
                currentStreak++
            } else {
                break
            }
            previousDate = date
        }

        // Check for broken streak even with previous entries
        return if (currentStreak == 0 && sortedDates.size > 1) {
            0  // No streak or broken streak
        } else {
            currentStreak
        }
    }

    // Function to calculate the difference in days between two dates
    private fun daysBetween(date1: Date, date2: Date): Long {
        val diffInMs = abs(date1.time - date2.time)
        return diffInMs / (1000 * 60 * 60 * 24)
    }

    fun isSameDay(date1: Date, date2: Date): Boolean {

        val calendar1 = Calendar.getInstance().apply { time = date1 }
        val calendar2 = Calendar.getInstance().apply { time = date2 }

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }
    fun getElapsedTime(date: Date): String {
        val now = Calendar.getInstance().timeInMillis
        val diff = now - date.time

        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val months = days / 30

        return when {
            months > 0 -> "$months months ago"
            days > 0 -> "$days days ago"
            hours > 0 -> "$hours hours ago"
            minutes > 0 -> "$minutes minutes ago"
            else -> "just now"
        }
    }
}