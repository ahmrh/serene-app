package com.ahmrh.serene.domain.usecase.selfcare.activity

import com.ahmrh.serene.domain.usecase.selfcare.activity.GetActivitiesUseCase
import com.ahmrh.serene.domain.usecase.selfcare.activity.GetActivityUseCase
import javax.inject.Inject

data class ActivityUseCases @Inject constructor(
    val getActivities: GetActivitiesUseCase,
    val getActivity: GetActivityUseCase
)