package com.ahmrh.serene.domain.handler

import androidx.compose.runtime.collectAsState
import com.ahmrh.serene.common.enums.ChallengeType
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.domain.model.gamification.Challenge
import com.google.android.play.integrity.internal.i
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChallengeHandler @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val gamificationRepository: GamificationRepository
){

    private var personalizationCategory : Category? = null

    private val challengeList: MutableList<Challenge> = mutableListOf()

//    private val listTodayChallenge: MutableList<Challenge> = generateTodayChallenge()

    init {
        getPersonalizationResultCategory()
    }

    private fun getPersonalizationResultCategory(){
        CoroutineScope(Dispatchers.IO).launch {
            val category = preferencesRepository.getPersonalizationResultValue().singleOrNull()
            category?.let { personalizationCategory = it }
        }
    }
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

    private fun resetChallenge() = challengeList.clear()







    fun addProgress(){

    }


    companion object {
        const val TAG = "ChallengeHandler"
    }

}
