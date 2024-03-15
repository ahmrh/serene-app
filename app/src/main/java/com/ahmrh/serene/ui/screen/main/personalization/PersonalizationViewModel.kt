package com.ahmrh.serene.ui.screen.main.personalization

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.utils.CategoryUtils
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.domain.model.PersonalizationPoint
import com.ahmrh.serene.domain.model.PersonalizationQuestion
import com.ahmrh.serene.domain.usecase.selfcare.personalization.PersonalizationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalizationViewModel @Inject constructor(

    private val preferencesRepository: PreferencesRepository,
    private val personalizationUseCases: PersonalizationUseCases
) : ViewModel() {

    companion object {
        const val TAG = "Personalization View Model"
    }

    // Personalization Screen
    private var _personalizationTypeState: MutableStateFlow<PersonalizationType> =
        MutableStateFlow(PersonalizationType.BASE)

    val personalizationTypeState: StateFlow<PersonalizationType>
        get() = _personalizationTypeState


    // Frequency Question
    private var _questionListState: MutableStateFlow<UiState<List<PersonalizationQuestion>>> =
        MutableStateFlow(UiState.Loading)
    val questionListState: StateFlow<UiState<List<PersonalizationQuestion>>>
        get() = _questionListState

    private var _personalizationPoint: MutableStateFlow<PersonalizationPoint> =
        MutableStateFlow(PersonalizationPoint())
    val personalizationPoint: StateFlow<PersonalizationPoint>
        get() = _personalizationPoint

    private var _personalizationPointList: MutableList<PersonalizationPoint> =
        mutableListOf()
    val personalizationPointList: List<PersonalizationPoint>
        get() = _personalizationPointList


    // Base Question
    private var _leastPracticedCategory: MutableList<Category> =
        mutableListOf()

    val leastPracticedCategory: List<Category>
        get() = _leastPracticedCategory


    // Result
    private var _resultCategoryState: MutableStateFlow<Int?> =
        MutableStateFlow(null)

    val resultCategoryState: StateFlow<Int?>
        get() = _resultCategoryState


    fun addLeastPracticedCategory(category: Category) {
        _leastPracticedCategory.add(category)
        println(leastPracticedCategory)
    }

    fun removeLeastPracticedCategory(category: Category) {
        _leastPracticedCategory.remove(category)
        println(leastPracticedCategory)
    }

    fun answerQuestion(category: Category, answerString: String) {
        val personalizationPoint = PersonalizationPoint()
        val point = when (FrequencyAnswer.fromString(answerString)) {
            FrequencyAnswer.NEVER -> 5
            FrequencyAnswer.RARELY -> 4
            FrequencyAnswer.OCCASIONALLY -> 3
            FrequencyAnswer.SOMETIMES -> 2
            FrequencyAnswer.REGULARLY -> 1
        }
        when (category) {
            Category.EMOTIONAL -> personalizationPoint.emotional += point
            Category.ENVIRONMENTAL -> personalizationPoint.environmental += point
            Category.PHYSICAL -> personalizationPoint.physical += point
            Category.RECREATIONAL -> personalizationPoint.recreational += point
            Category.MENTAL -> personalizationPoint.mental += point
            Category.SOCIAL -> personalizationPoint.social += point
            Category.SPIRITUAL -> personalizationPoint.spiritual += point
        }

        _personalizationPointList.add(personalizationPoint)
        Log.d(TAG, "$personalizationPointList")
    }

    fun revertAnswerQuestion() {
        _personalizationPointList.removeLast()
        Log.d(TAG, "$personalizationPointList")
    }


    fun fetchQuestionList() {
        viewModelScope.launch {
            personalizationUseCases.getQuestion(leastPracticedCategory)
                .collect {

                    when (it) {
                        is ResourceState.Success -> {
                            Log.d(TAG, "${it.data}")
                            _questionListState.value =
                                UiState.Success(it.data!!)
                        }

                        is ResourceState.Failed -> {
                            _questionListState.value = UiState.Error(
                                it.exception?.message ?: "Unexpected Error"
                            )
                        }
                    }
                }

        }
    }


    fun calculateResult() {

        viewModelScope.launch {

            val pointSum = mutableListOf(0, 0, 0, 0, 0, 0, 0)

            _personalizationPointList.forEach {
                pointSum[0] += it.emotional
                pointSum[1] += it.environmental
                pointSum[2] += it.mental
                pointSum[3] += it.physical
                pointSum[4] += it.recreational
                pointSum[5] += it.social
                pointSum[6] += it.spiritual
            }

            val maxIndex = pointSum.indexOf(pointSum.maxOrNull())

            val category = CategoryUtils.getCategory(maxIndex + 1)

            _resultCategoryState.value = category.id

            preferencesRepository.changePersonalizationResultValue(
                category
            )
            personalizationUseCases.savePersonalizationResult(category)
        }

    }

    fun changeToQuestionType() {
        _personalizationTypeState.value = PersonalizationType.QUESTION
    }

//    fun changeToResultType(){
//        _personalizationTypeState.value = PersonalizationType.RESULT
//    }

    fun changeToBaseType() {
        _personalizationTypeState.value = PersonalizationType.BASE
    }

}

enum class PersonalizationType {
    BASE,
//    RESULT,
    QUESTION
}

enum class FrequencyAnswer(val stringValue: String) {
    REGULARLY("Regularly"),
    SOMETIMES("Sometimes"),
    OCCASIONALLY("Occasionally"),
    RARELY("Rarely"),
    NEVER("Never");


    companion object {
        fun fromString(string: String): FrequencyAnswer =
            FrequencyAnswer.entries.single { it.stringValue == string }
    }
}