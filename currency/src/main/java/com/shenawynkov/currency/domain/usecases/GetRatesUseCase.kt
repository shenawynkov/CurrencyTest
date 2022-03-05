package com.shenawynkov.currency.domain.usecases

import com.shenawynkov.currency.Resource
import com.shenawynkov.currency.domain.repo.CurrenciesRepo
import com.shenawynkov.currency.domain.model.Rate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(private val repo: CurrenciesRepo){
    operator fun invoke(): Flow<Resource<List<Rate>>> =repo.getRates()
}