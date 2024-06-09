package com.kemakolam.republicserviceapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kemakolam.republicserviceapp.data.db.tables.RouteEntity

@Dao // Data Access Object-> Class/object used to access data in the Database
interface RoutesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutes(routes: List<RouteEntity>)

    @Query("SELECT * FROM RouteEntity")
    suspend fun getAllRoutes(): List<RouteEntity>

}
