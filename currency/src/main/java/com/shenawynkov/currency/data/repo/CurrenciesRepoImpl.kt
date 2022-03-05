package com.shenawynkov.currency.data.repo

import com.shenawynkov.currency.Resource
import com.shenawynkov.currency.data.api.ApiService
import com.shenawynkov.currency.data.api.Constants.key
import com.shenawynkov.currency.data.db.CurrenciesDatabase
import com.shenawynkov.currency.data.mapper.mapToRateEntity
import com.shenawynkov.currency.data.mapper.mapToRates
import com.shenawynkov.currency.data.mapper.toListOfRates
import com.shenawynkov.currency.domain.repo.CurrenciesRepo
import com.shenawynkov.currency.domain.model.Rate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CurrenciesRepoImpl(private val apiService: ApiService, private val db: CurrenciesDatabase) :CurrenciesRepo {
    override fun getRates(): Flow<Resource<List<Rate>>> =flow{




            try {
                //emit loading until receiving data
                emit(Resource.Loading<List<Rate>>())
                //emit  data
                val result= apiService.getRates(key = key).rates.toListOfRates()
                emit(Resource.Success(result))
                //updateDb
                updateRates(result)
            }
            catch (e: IOException) {
                emit(
                    Resource.Error<List<Rate>>(
                        "Couldn't reach server. Check your internet connection.",
                        getRatesFromDB()
                    )
                )}

            catch (e: Exception) {
                emit(
                    Resource.Error<List<Rate>>(
                        e.localizedMessage ?: "An unexpected error occurred", getRatesFromDB()
                    )
                )
            }



    }

   private suspend fun getRatesFromDB():List<Rate>
    {
       return db.ratesDoa().getRates().mapToRates()
    }
    private suspend fun updateRates(result: ArrayList<Rate>)
    {
        result.forEach {
            db.ratesDoa().saveRate(it.mapToRateEntity())
        }

    }
}