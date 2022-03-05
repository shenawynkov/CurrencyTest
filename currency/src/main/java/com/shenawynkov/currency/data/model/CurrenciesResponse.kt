package com.shenawynkov.currency.data.model

import com.google.gson.annotations.SerializedName

data class CurrenciesResponse(
    val base: String,
    val date: String,
    @SerializedName("rates")
    val rates: Map<String, Double>,
    val success: Boolean,
    val timestamp: Int
)