package com.ahmrh.serene.ui.screen.main.personalization

import androidx.lifecycle.ViewModel
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.state.UiState
import com.ahmrh.serene.data.source.local.room.entity.personalization.Question
import com.ahmrh.serene.ui.screen.main.personalization.question.QuestionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PersonalizationViewModel @Inject constructor(

): ViewModel() {

    // to differentiate selfcare category question and frequency question
    private var _questionTypeState: MutableStateFlow<QuestionType> =
        MutableStateFlow(QuestionType.SelfCare)
    val questionTypeState: MutableStateFlow<QuestionType>
        get() = _questionTypeState


    private var _currentQuestionState: MutableStateFlow<UiState<Question>> =
        MutableStateFlow(UiState.Loading)
    val currentQuestionState: MutableStateFlow<UiState<Question>>
        get() = _currentQuestionState

    fun changeQuestionTypeState(questionType: QuestionType){
        _questionTypeState.update{
            questionType
        }

    }

    fun getQuestionList(questionCategory: List<Category>){


    }





    init {


    }

}