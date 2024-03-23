package com.ahmrh.serene.common.utils

import java.util.Calendar
import java.util.Date

object DateUtils {

    fun getDayStreak(dates: List<Date>): Int {
        val today = Date() // Get today's date

        // Sort dates in ascending order
        val sortedDates = dates.sortedBy { it }

        if (sortedDates.isEmpty()) {
            return 0
        }

        var currentStreak = 1
        var previousDate = sortedDates[0]

        for (date in sortedDates.subList(1, sortedDates.size)) {
            val daysBetween = getDaysBetween(previousDate, date)
            if (daysBetween > 1) {
                currentStreak = 0
            } else {
                currentStreak++
            }
            previousDate = date
        }

        val expectedDate = addDaysToDate(previousDate, currentStreak - 1)
        if (isSameDay(expectedDate, today)) {
            return currentStreak
        } else {
            return 0
        }
    }

    // Helper functions for Date manipulation
    private fun getDaysBetween(date1: Date, date2: Date): Int {
        // Calculate days between dates using a Calendar instance
        val calendar = Calendar.getInstance()
        calendar.time = date1
        val days1 = calendar.get(Calendar.DAY_OF_YEAR)
        calendar.time = date2
        val days2 = calendar.get(Calendar.DAY_OF_YEAR)
        return days2 - days1
    }

    private fun addDaysToDate(date: Date, days: Int): Date {
        // Add days to a Date using a Calendar instance
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return calendar.time
    }

    private fun isSameDay(date1: Date, date2: Date): Boolean {
        // Check if two dates represent the same calendar day
        val calendar1 = Calendar.getInstance()
        calendar1.time = date1
        val calendar2 = Calendar.getInstance()
        calendar2.time = date2
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }
}