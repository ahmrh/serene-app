package com.ahmrh.serene.domain.usecase.selfcare.activity

import javax.inject.Inject

data class ActivityUseCases @Inject constructor(
    val getActivities: GetActivitiesUseCase,
    val getActivity: GetActivityUseCase,
    val getRecommendation: GetRecommendationUseCase,
    val practiceSelfCare: PracticeSelfCareUseCase,
)