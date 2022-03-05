package com.shenawynkov.data.db

import androidx.room.*

@Dao
interface RateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRate(vararg rateEntity: RateEntity)


    @Query("SELECT * FROM rate WHERE key=:key")
    suspend fun getRate(key: String): RateEntity


    @Query("SELECT * FROM rate")
    suspend fun getRates(): List<RateEntity>

}