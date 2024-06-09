package com.kemakolam.republicserviceapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kemakolam.republicserviceapp.data.db.tables.Route

@Dao // Data Access Object-> Class/object used to access data in the Database
interface RoutesDao {

    @Query("SELECT * From Route")
    suspend fun getAllDrivers():List<Route>

    @Insert
    suspend fun insertDriver(route:Route)

}
