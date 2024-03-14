package com.ahmrh.serene.domain.usecase.selfcare.personalization

import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.repository.PersonalizationRepository
import com.ahmrh.serene.domain.model.PersonalizationQuestion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
    private val repository: PersonalizationRepository

){

    operator fun invoke(): Flow<ResourceState<List<PersonalizationQuestion>>> = repository.fetchQuestions()

    operator fun invoke(
        listCategory: List<Category>
    ) : Flow<ResourceState<List<PersonalizationQuestion>>> = repository.fetchQuestions(listCategory)

}