package com.ahmrh.serene.domain.usecase.selfcare.personalization

import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.repository.PersonalizationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
    private val repository: PersonalizationRepository

){

    operator fun invoke(): Flow<ResourceState<List<String>>> = repository.fetchQuestions()

    operator fun invoke(
        listCategory: List<Category>
    ) : Flow<ResourceState<List<String>>> = repository.fetchQuestions(listCategory)

}