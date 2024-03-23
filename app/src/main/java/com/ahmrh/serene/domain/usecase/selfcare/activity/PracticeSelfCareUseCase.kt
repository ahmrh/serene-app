package com.ahmrh.serene.domain.usecase.selfcare.activity

import android.util.Log
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.domain.model.gamification.Achievement
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import com.ahmrh.serene.domain.model.user.SelfCareHistory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class PracticeSelfCareUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val gamificationRepository: GamificationRepository
) {
    suspend operator fun invoke(selfCareActivity: SelfCareActivity, onAchievementUnlocked: (String?) -> Unit, onResult: (Throwable?) -> Unit){
        val category = selfCareActivity.category!!
        userRepository.addSelfCareHistory(selfCareActivity.id, category, onResult)

        val totalSelfCarePracticed = userRepository.getTotalPracticedSelfCareByCategory(category)

        Log.d(TAG, "Total: $totalSelfCarePracticed")
        if(totalSelfCarePracticed == 7 || totalSelfCarePracticed == 3 || totalSelfCarePracticed == 1) {
            val achievementId = gamificationRepository.getAchievementIdByProgressCondition(progress = totalSelfCarePracticed, categoryString = category)

            Log.d(TAG, "Total: $totalSelfCarePracticed")
            userRepository.addAchievementById(achievementId){
                if(it != null) Log.e(TAG, "Error: $it")
            }
            onAchievementUnlocked(achievementId)
        } else {
            onAchievementUnlocked(null)
        }

    }

    companion object{
        const val TAG = "PracticeSelfCareUseCase"
    }

}