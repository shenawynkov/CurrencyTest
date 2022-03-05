package com.shenawynkov.currency.domain.usecases

import com.shenawynkov.currency.Resource
import com.shenawynkov.currency.domain.repo.CurrenciesRepo
import com.shenawynkov.currency.domain.model.Rate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConvertRateUseCase @Inject constructor(){
    operator fun invoke( base:Double,rate:Double)=base*rate
}