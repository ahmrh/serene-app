package com.ahmrh.serene.common.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.abs

object DateUtils {
    const val TAG = "DateUtils"
    fun getYesterdayDate(): Date {
        val currentDate = Date()
        val yesterdayMillis = currentDate.time - (1000 * 60 * 60 * 24)
        return Date(yesterdayMillis)
    }

    fun getDayOfMonth(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun getYearMonthDayFormat(date: Date): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")

        return formatter.format(date)
    }


    fun formatJoinedDate(date: Date): String {
        val monthYearDateFormat = SimpleDateFormat("MMMM yyyy", Locale.US)
        return "Joined " + monthYearDateFormat.format(date)
    }

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

     fun calculateStreak(dateList: List<Date>): Int {
        var streak = 1 // Start with 1 to count the first date in the streak
        for (i in 1 until dateList.size) {
            val previousDate = Calendar.getInstance().apply { time = dateList[i - 1] }
            val currentDate = Calendar.getInstance().apply { time = dateList[i] }
            previousDate.add(Calendar.DATE, -1)

            if (DateUtils.isSameDay(previousDate.time, currentDate.time)) {
                streak++
            } else {
                break
            }
        }
        return streak
    }

    // Function to calculate the difference in days between two dates
    fun daysBetween(date1: Date, date2: Date): Long {
        val diffInMs = abs(date1.time - date2.time)
        return diffInMs / (1000 * 60 * 60 * 24)
    }

    fun isSameDay(date1: Date, date2: Date): Boolean {

        val calendar1 = Calendar.getInstance().apply { time = date1 }
        val calendar2 = Calendar.getInstance().apply { time = date2 }

        return calendar1.get(Calendar.YEAR) == calendar2.get(
            Calendar.YEAR
        ) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(
            Calendar.DAY_OF_YEAR
        )
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