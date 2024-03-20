package com.ahmrh.serene.domain.usecase.selfcare.activity

import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.common.utils.Category
import com.ahmrh.serene.data.repository.PersonalizationRepository
import com.ahmrh.serene.domain.model.selfcare.SelfCareRecommendation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendationUseCase @Inject constructor(
    private val repository: PersonalizationRepository
)  {
    operator fun invoke(
        category: Category
    ): Flow<ResourceState<List<SelfCareRecommendation>>> = repository.fetchRecommendation(category)
}