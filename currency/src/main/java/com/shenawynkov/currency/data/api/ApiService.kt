package com.shenawynkov.currency.data.api


import com.shenawynkov.currency.data.api.Constants.latest
import com.shenawynkov.currency.data.model.CurrenciesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {

    @GET(latest)
    suspend fun getRates(@Query("access_key")key:String,): CurrenciesResponse

}