package com.ahmrh.serene.domain.usecase.selfcare.personalization

import javax.inject.Inject

data class PersonalizationUseCases @Inject constructor(
    val getResult: GetResultUseCase,
    val getQuestion: GetQuestionUseCase,
)