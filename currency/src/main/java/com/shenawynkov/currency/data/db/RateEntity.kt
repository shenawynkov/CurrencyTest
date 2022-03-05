package com.shenawynkov.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "rate")
data class RateEntity(

    @PrimaryKey  val key: String,
    @ColumnInfo(name = "value") val value: Double

)