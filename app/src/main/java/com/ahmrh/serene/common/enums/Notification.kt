package com.ahmrh.serene.common.enums

import java.util.concurrent.TimeUnit

enum class Notification(
    val id: Int,
    val title: String,
    val message: String,
    val time: Long,
    val timeUnit: TimeUnit,
) {
    TYPE_1(
        1,
        "Self-care Reminder",
        "It's time for your daily Self-care practice. Make sure to check out the app.",
        5L,
        TimeUnit.SECONDS

    ),
    TYPE_2(
        2,
        "Self-care Reminder",
        "It seems you don't practice Self-care yesterday. Make sure to check out the app.",
        10L,
        TimeUnit.SECONDS
    ),

    TYPE_3(
        3,
        "Hey,",
        "It's been a while, don't forget to practice Self-care whenever you have free time.",
        15L,
        TimeUnit.SECONDS
    ),

    TYPE_4(
        4,
        "Hey,",
        "I know you have been through a lot. You can check out this app again if you have time.",
        20L,
        TimeUnit.SECONDS
    ),


    TYPE_5(
        5,
        "Well,",
        "These reminders don't seem to be working. We'll stop sending them for now.",
        25L,
        TimeUnit.SECONDS
    ),

    DEFAULT(
        0,
        "Default Notification",
        "Default Notification Message",
        1L,
        TimeUnit.SECONDS

    );

    companion object {
        fun fromId(id: Int): Notification =
            Notification.entries.single { it.id == id }

        fun getId(): Int =
            Notification.entries.single().id
    }


}