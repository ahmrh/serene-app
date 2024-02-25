package com.ahmrh.serene.domain.usecase.selfcare.personalization

import com.ahmrh.serene.common.state.ResourceState
import com.ahmrh.serene.data.repository.PersonalizationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetResultUseCase @Inject constructor(
    private val repository: PersonalizationRepository

){

    operator fun invoke(

    ): Flow<ResourceState<List<String>>> = flow {
//        try{
//            repository.getAllQuestionsStream()
//                .catch{
//                    throw(it)
//                }
//                .collect{
//                    emit(ResourceState.Success(it))
//                }
//        }
//        catch(e: Exception){
//            emit(ResourceState.Error(message = e.localizedMessage ?: "An unexpected error occured"))
//        }
    }

    
}