package com.ahmrh.serene.domain.usecase.selfcare.activity

import android.util.Log
import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.enums.Sentiment
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.handler.NotificationHandler
import com.ahmrh.serene.domain.model.gamification.DailyStreak
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class PracticeSelfCareUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val gamificationRepository: GamificationRepository,
    private val notificationHandler: NotificationHandler
) {
    suspend operator fun invoke(
        selfCareActivity: SelfCareActivity,
        sentiment: Sentiment,
        onAchievementUnlockedEvent: (String?) -> Unit,
        onDailyStreakEvent: (DailyStreak?) -> Unit,
        onResult: (Throwable?) -> Unit,
    ) {

        addSelfCareHistory(selfCareActivity, sentiment)
        onAchievementUnlockedEvent(
            getUnlockedAchievement(selfCareActivity)
        )
        onDailyStreakEvent(getDailyStreak())
        scheduleReminderNotification()
        addChallengeProgress(
            selfCareActivity.category?.let {
                CategoryUtils.getCategory(
                    it
                )
            }
        )

    }

    private suspend fun addChallengeProgress( category: Category?){
        category?.let{
            val challengeType = ChallengeType.PRACTICE(it)

            gamificationRepository.addChallengeProgress(challengeType)
        }
    }

    private fun scheduleReminderNotification(){
        notificationHandler.cancelAllNotification()

        notificationHandler.scheduleSelfCareReminderNotification()
    }

    private suspend fun addSelfCareHistory(selfCareActivity: SelfCareActivity, sentiment: Sentiment) {
        userRepository.addSelfCareHistory(selfCareActivity, sentiment) {
            if (it != null) Log.e(TAG, "Error: $it")
        }
    }

    private suspend fun getUnlockedAchievement(
        selfCareActivity: SelfCareActivity
    ): String? {
        val category = selfCareActivity.category!!

        val totalSelfCarePracticed =
            userRepository.getTotalPracticedSelfCareByCategory(category)

        var achievementId: String? = null

        if (totalSelfCarePracticed == 7 || totalSelfCarePracticed == 3 || totalSelfCarePracticed == 1) {
            achievementId =
                gamificationRepository.getAchievementIdByProgressCondition(
                    progress = totalSelfCarePracticed,
                    categoryString = category
                )

            Log.d(TAG, "Total: $totalSelfCarePracticed")
            userRepository.addAchievementById(achievementId) {
                if (it != null) Log.e(TAG, "Error: $it")
            }

        }

        Log.d(TAG, "Achievement unlocked, id: $achievementId")
        return achievementId
    }

    private suspend fun getDailyStreak(): DailyStreak? {
        val selfCareHistoryList = userRepository.fetchSelfCareHistoryList()
        if (selfCareHistoryList.isEmpty()) return null

        val dateList = selfCareHistoryList.map { it.date }
            .sortedByDescending { it.time }

        val todayDate = Calendar.getInstance().time

        if (DateUtils.isSameDay(todayDate, dateList[0])) {
            // Calculate streak including today
            val dayStreakCount = DateUtils.calculateStreak(dateList)
            return DailyStreak(dayStreakCount, todayDate)
        }
        else {
            // No self-care activity for today, return streak up to yesterday
            val yesterdayDate = Calendar.getInstance().apply { add(Calendar.DATE, -1) }.time
            if (DateUtils.isSameDay(yesterdayDate, dateList[0])) {
                val dayStreakCount = DateUtils.calculateStreak(dateList)
                return DailyStreak(dayStreakCount, yesterdayDate)
            } else {
                return DailyStreak(0, todayDate)
            }
        }
//        if (dateList.size >= 2) {
//
//            val previousDate = dateList[1]
//
//            if (DateUtils.isSameDay(todayDate, previousDate)) {
//                return null
//            }
//        }
//
//        val dayStreakCount = DateUtils.getDayStreak(dateList)
//        return DailyStreak(dayStreakCount, todayDate)

    }

    companion object {
        const val TAG = "PracticeSelfCareUseCase"
    }

}