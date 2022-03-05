package com.shenawynkov.currency.domain.repo

import com.shenawynkov.currency.Resource
import com.shenawynkov.currency.domain.model.Rate
import kotlinx.coroutines.flow.Flow

interface CurrenciesRepo {
    fun getRates():Flow<Resource<List<Rate>>>
}