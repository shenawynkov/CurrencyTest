package com.shenawynkov.currency.data.mapper

import com.shenawynkov.currency.domain.model.Rate
import com.shenawynkov.data.db.RateEntity

fun RateEntity.mapToRate()= run { Rate(key, value) }
fun List<RateEntity>.mapToRates():List<Rate>{
    return map {
        Rate(key = it.key, value = it.value)
    }
}

fun Map<String,Double>.toListOfRates(): ArrayList<Rate> {
    val list=ArrayList<Rate>()
    forEach { (k, v) ->
        list.add(Rate(k,v))
    }
    return list
}
fun Rate.mapToRateEntity()=RateEntity(key, value)