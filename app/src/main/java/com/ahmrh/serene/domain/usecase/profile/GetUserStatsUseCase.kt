package com.ahmrh.serene.domain.usecase.profile

import com.ahmrh.serene.common.utils.ArrayUtils
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.user.UserStats
import com.google.android.gms.tasks.OnFailureListener
import javax.inject.Inject

class GetUserStatsUseCase @Inject constructor(
    private val userRepository: UserRepository
){

    suspend operator fun invoke(
        onSuccess: (UserStats) -> Unit,
        onFailure: (Throwable?) -> Unit
    ){
        try {

            val historyList = userRepository.fetchSelfCareHistoryList()!!

            val achievementList = userRepository.fetchAchievementsList()!!


            val dayStreak = DateUtils.getDayStreak(
                historyList.map {
                    it.date
                }
            )

            val topSelfCare = ArrayUtils.getMaxOccurringString(
                historyList.map {
                    it.selfCareCategory
                }
            )

            val totalSelfCare = historyList.size
            val totalAchievement = achievementList.size

            val userStats = UserStats(
                dayStreak = dayStreak,
                topSelfCare = topSelfCare,
                totalSelfCare = totalSelfCare,
                totalAchievement = totalAchievement
            )

            onSuccess(userStats)
        } catch(e: Exception){
            onFailure(e)


        }
    }
}