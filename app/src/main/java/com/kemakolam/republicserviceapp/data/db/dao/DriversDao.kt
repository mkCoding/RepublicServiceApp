package com.kemakolam.republicserviceapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import java.sql.Driver


@Dao
interface DriversDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrivers(driver: List<DriverEntity>)
    @Query("SELECT * From DriverEntity")
    suspend fun getAllDrivers():List<DriverEntity>

    @Query ("SELECT * From DriverEntity where id =:driverId")
    suspend fun getDriverById(driverId:String):DriverEntity?




}