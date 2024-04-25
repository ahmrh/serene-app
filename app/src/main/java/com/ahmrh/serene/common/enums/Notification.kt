package com.ahmrh.serene.common.enums

import java.util.concurrent.TimeUnit

enum class Notification(
    val id: Int,
    val title: String,
    val message: String,
    val day: Long,
) {
    TYPE_1(
        1,
        "Self-care Reminder",
        "It's time for your daily Self-care practice. Make sure to check out the app today.",
        1L,
    ),
    TYPE_2(
        2,
        "Self-care Reminder",
        "It seems you don't practice Self-care yesterday. Make sure to check out the app this day.",
        2L,
    ),

    TYPE_3(
        3,
        "Hey,",
        "It's been a while, don't forget to practice Self-care whenever you have free time.",
        7L,
    ),

    TYPE_4(
        4,
        "Hey,",
        "I know you have been through a lot. You can check out this app again if you have time.",
        10L,
    ),


    TYPE_5(
        5,
        "Well,",
        "These reminders don't seem to be working. We'll stop sending them for now.",
        14L,
    ),

    DEFAULT(
        0,
        "Default Notification",
        "Default Notification Message",
        -1L,

    );

    companion object {
        fun fromId(id: Int): Notification =
            Notification.entries.single { it.id == id }

        fun fromDays(dayDifference: Long): Notification =
            Notification.entries.single{ it.day == dayDifference }

        fun getId(): Int =
            Notification.entries.single().id
    }


}