package com.ahmrh.serene.domain.usecase.selfcare.personalization

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.repository.PersonalizationRepository
import com.ahmrh.serene.data.source.local.room.entity.personalization.Question
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PersonalizationUseCase @Inject constructor(
    val getResult: GetResultUseCase,
    val getQuestion: GetQuestionUseCase,

){

    operator fun invoke() {

    }
}