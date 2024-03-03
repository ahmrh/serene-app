package com.ahmrh.serene.ui.screen.main.personalization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.repository.PreferencesRepository
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
): ViewModel() {

    companion object{
        const val TAG = "Personalization View Model"
    }


    private var _personalizationTypeState: MutableStateFlow<PersonalizationType> =
        MutableStateFlow(PersonalizationType.BASE)

    val personalizationTypeState: StateFlow<PersonalizationType>
        get() = _personalizationTypeState


    private var _isFirstTimeUsage: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isFirstTimeUsage: StateFlow<Boolean>
        get() = _isFirstTimeUsage


    private var _questionIndexState: MutableStateFlow<Int> =
        MutableStateFlow(0)
    val questionIndexState: StateFlow<Int>
        get() = _questionIndexState



    private var _questionListState: MutableStateFlow<List<String>> =
        MutableStateFlow(listOf())
    val questionListState: StateFlow<List<String>>
        get() = _questionListState



    private var _leastPracticedCategory: MutableList<Category> =
        mutableListOf()

    val leastPracticedCategory: List<Category>
        get() = _leastPracticedCategory



    init {
        fetchQuestionList()
    }

    fun addLeastPracticedCategory(category: Category){
        _leastPracticedCategory.add(category)
        println(leastPracticedCategory)
    }

    fun removeLeastPracticedCategory(category: Category){
        _leastPracticedCategory.remove(category)
        println(leastPracticedCategory)
    }


    private fun fetchQuestionList(){
        viewModelScope.launch{
            personalizationUseCases.getQuestion().collect {
                when(it){
                    is ResourceState.Success -> {
                        _questionListState.value = it.data!!
                    }
                    is ResourceState.Failed -> {
                        throw(it.exception!!)
                    }
                }
            }

        }
    }


    fun getQuestionList(questionCategory: List<Category>){


    }

    fun changePersonalizationType(){
        _per
    }




}

enum class PersonalizationType{
    BASE,
    RESULT,
    QUESTION
}