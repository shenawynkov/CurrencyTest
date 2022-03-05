package com.shenawynkov.currency.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shenawynkov.data.db.RateDao
import com.shenawynkov.data.db.RateEntity


@Database(entities = [ RateEntity::class], version = 1)
abstract class CurrenciesDatabase : RoomDatabase() {

    abstract fun ratesDoa(): RateDao


}