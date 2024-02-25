package com.ahmrh.serene.domain.usecase.selfcare.activity

import android.net.Uri
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.repository.SelfCareRepository
import com.ahmrh.serene.domain.model.SelfCareActivity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSupportingImageUseCase @Inject constructor(

    private val selfCareRepository: SelfCareRepository
) {
    operator fun invoke(
        supportingImage: String
    ): Flow<ResourceState<Uri>> =
        selfCareRepository.fetchSupportingImageUri(supportingImage)
}