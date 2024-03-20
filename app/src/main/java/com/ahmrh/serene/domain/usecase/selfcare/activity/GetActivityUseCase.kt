package com.ahmrh.serene.domain.usecase.selfcare.activity

import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.repository.SelfCareRepository
import com.ahmrh.serene.domain.model.selfcare.SelfCareActivity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val selfCareRepository: SelfCareRepository
) {

    operator fun invoke(id: String): Flow<ResourceState<SelfCareActivity>> =
        selfCareRepository.fetchActivity(id)

}