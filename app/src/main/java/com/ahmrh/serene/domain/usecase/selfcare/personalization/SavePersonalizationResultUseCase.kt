package com.ahmrh.serene.domain.usecase.selfcare.personalization

import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.repository.PersonalizationRepository
import javax.inject.Inject

class SavePersonalizationResultUseCase @Inject constructor(
    private val repository: PersonalizationRepository
){

    operator fun invoke(category: Category){
        repository.savePersonalizationResult(category)
    }
}