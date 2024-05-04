package com.ahmrh.serene.domain.handler

import android.util.Log
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.DateUtils
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.domain.model.gamification.Challenge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChallengeHandler @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val gamificationRepository: GamificationRepository
) {

//    private var personalizationCategory: Category? = null

    private var _todayChallengeListState: MutableStateFlow<List<Challenge>?> =
        MutableStateFlow(null)
    val todayChallengeList: StateFlow<List<Challenge>?>
        get() = _todayChallengeListState


    init {
        CoroutineScope(Dispatchers.IO).launch {

//            fetchTodayChallenge()
            Log.d(TAG, "Challenge initialization done")
        }


    }

//    private suspend fun fetchTodayChallenge() {
//        val category =
//            preferencesRepository.getPersonalizationResultValue()
//                .singleOrNull()
//
//        val lastFetchedDate = preferencesRepository.getChallengeFetchedDateValue().singleOrNull()
//
//        val todayDate = Date()
//
//        val shouldFetch =
//            if (lastFetchedDate != null) !DateUtils.isSameDay(
//                lastFetchedDate, todayDate
//            ) else true
//
//        Log.d(TAG, "Last fetched date: $lastFetchedDate")
//        Log.d(TAG, "Should fetch: $shouldFetch")
//        Log.d(TAG, "Personalization category: $category")
//        if (shouldFetch) {
//            gamificationRepository.fetchTodayChallenges(
//                category,
//                onSuccess = {
//                    _todayChallengeListState.value = it
//                    Log.d(TAG, "Challenge list: $it")
//                },
//                onFailure = {
//                    Log.d(
//                        TAG,
//                        "Error fetching challenge: $it"
//                    )
//                }
//            )
//            preferencesRepository.changeChallengeFetchedDateValue(
//                todayDate.time
//            )
//
//        }
//    }



//    fun generateTodayChallenge(): List<Challenge>{
//        val todayDate = Calendar.getInstance().time.day
//
//
//    }


//    private fun generateChallenge(){
//
//        Category.entries.forEach{ category ->
//            challengeList.add(
//                Challenge(
//                    challengeType = ChallengeType.PRACTICE(category),
//
//                    name = "Practice ${category.stringValue} Self-care Activity today",
//                    progress = 0,
//                    isDone = false
//                )
//            )
//        }
//
//        challengeList.add(
//            Challenge(
//                challengeType = ChallengeType.PERSONALIZATION,
//                name = "Get your personalization today",
//                progress = 0,
//                isDone = false
//            )
//        )
//    }

//    private fun resetChallenge() = challengeList.clear()


fun addProgress() {

}


companion object {
    const val TAG = "ChallengeHandler"
}

}
