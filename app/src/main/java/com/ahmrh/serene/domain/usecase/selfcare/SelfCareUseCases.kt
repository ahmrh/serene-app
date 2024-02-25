package com.ahmrh.serene.domain.usecase.selfcare

import com.ahmrh.serene.domain.usecase.selfcare.activity.GetActivitiesUseCase
import com.ahmrh.serene.domain.usecase.selfcare.activity.GetActivityUseCase
import com.ahmrh.serene.domain.usecase.selfcare.personalization.PersonalizationUseCase
import javax.inject.Inject

data class SelfCareUseCases @Inject constructor(
    val personalize: PersonalizationUseCase,
    val getActivities: GetActivitiesUseCase,
    val getActivity: GetActivityUseCase
)