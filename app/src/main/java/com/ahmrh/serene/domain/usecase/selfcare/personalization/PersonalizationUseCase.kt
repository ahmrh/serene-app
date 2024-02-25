package com.ahmrh.serene.domain.usecase.selfcare.personalization

import com.ahmrh.serene.common.Category
import javax.inject.Inject

class PersonalizationUseCase @Inject constructor(
    val getResult: GetResultUseCase,
    val getQuestion: GetQuestionUseCase,

){

    operator fun invoke(selfCareCategoryList: List<Category>) {


    }
}