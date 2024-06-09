package com.kemakolam.republicserviceapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kemakolam.republicserviceapp.data.db.tables.Driver


@Dao
interface DriversDao {
    @Query("SELECT * From Driver")
    suspend fun getAllDrivers():List<Driver>

    @Insert
    suspend fun insertDriver(driver: Driver)
}