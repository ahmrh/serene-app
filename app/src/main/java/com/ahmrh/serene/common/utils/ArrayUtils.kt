package com.ahmrh.serene.common.utils

object ArrayUtils {
    fun getMaxOccurringString(stringList: List<String>): String? {
        // Group strings by their occurrence using groupingBy and counting
        val groupedByOccurrence = stringList.groupingBy { it }.eachCount()

        // Find the entry with the maximum count
        val maxEntry = groupedByOccurrence.maxByOrNull { it.value } // Use maxByOrNull for empty list

        // If there's a maximum entry, return the string and its count
        return maxEntry?.let { Pair(it.key, it.value) }?.first

        // If there's no maximum or the list is empty, return null
    }
}