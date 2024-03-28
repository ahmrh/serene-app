package com.ahmrh.serene.domain.usecase.selfcare.activity

import android.util.Log
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.gamification.DailyStreak
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class PracticeSelfCareUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val gamificationRepository: GamificationRepository
) {
    suspend operator fun invoke(selfCareActivity: SelfCareActivity, onAchievementUnlockedEvent: (String?) -> Unit, onDailyStreakEvent: (DailyStreak?) -> Unit, onResult: (Throwable?) -> Unit){

        addSelfCareHistory(selfCareActivity)
        onAchievementUnlockedEvent(getUnlockedAchievement(selfCareActivity))
        onDailyStreakEvent(getDailyStreak())

    }

    private fun addSelfCareHistory(selfCareActivity: SelfCareActivity){
        userRepository.addSelfCareHistory(selfCareActivity){
            if(it != null) Log.e(TAG, "Error: $it")
        }
    }

    private suspend fun getUnlockedAchievement(selfCareActivity: SelfCareActivity): String?{
        val category = selfCareActivity.category!!

        val totalSelfCarePracticed = userRepository.getTotalPracticedSelfCareByCategory(category)

        var achievementId: String? = null

        if(totalSelfCarePracticed == 7 || totalSelfCarePracticed == 3 || totalSelfCarePracticed == 1) {
            achievementId = gamificationRepository.getAchievementIdByProgressCondition(progress = totalSelfCarePracticed, categoryString = category)

            Log.d(TAG, "Total: $totalSelfCarePracticed")
            userRepository.addAchievementById(achievementId){
                if(it != null) Log.e(TAG, "Error: $it")
            }

        }

        Log.d(TAG, "Achievement unlocked, id: $achievementId")
        return achievementId
    }

    private suspend fun getDailyStreak(): DailyStreak? {
        val selfCareHistoryList = userRepository.fetchSelfCareHistory()!!

        val dateList = selfCareHistoryList.map{ it.date }.sortedByDescending{ it.time }

        val todayDate = Calendar.getInstance().time

        if(dateList.size >= 2){

            val previousDate = dateList[1]

            if(DateUtils.isSameDay(todayDate, previousDate)){
                return null
            }
        }

        val dayStreakCount = DateUtils.getDayStreak(dateList)
        return DailyStreak(dayStreakCount, todayDate)

    }

    companion object{
        const val TAG = "PracticeSelfCareUseCase"
    }

}