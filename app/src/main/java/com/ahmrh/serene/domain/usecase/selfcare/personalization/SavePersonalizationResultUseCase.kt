package com.ahmrh.serene.domain.usecase.selfcare.personalization

import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.repository.PersonalizationRepository
import com.ahmrh.serene.domain.model.PersonalizationPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SavePersonalizationResultUseCase @Inject constructor(
    private val repository: PersonalizationRepository
){

    operator fun invoke(category: Category){
        repository.savePersonalizationResult(category)
    }
}