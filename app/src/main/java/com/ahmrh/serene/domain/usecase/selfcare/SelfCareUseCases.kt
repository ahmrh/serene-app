package com.ahmrh.serene.domain.usecase.selfcare

import com.ahmrh.serene.domain.usecase.selfcare.personalization.PersonalizationUseCase
import javax.inject.Inject

data class SelfCareUseCases @Inject constructor(
    val personalize: PersonalizationUseCase,
)