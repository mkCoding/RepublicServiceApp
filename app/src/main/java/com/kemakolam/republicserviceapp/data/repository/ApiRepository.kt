package com.kemakolam.republicserviceapp.data.repository

import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import com.kemakolam.republicserviceapp.data.db.tables.RouteEntity
import com.kemakolam.republicserviceapp.data.network.model.DriverModel
import com.kemakolam.republicserviceapp.data.network.model.RouteModel

interface ApiRepository {
    //Get Drivers and Routes specifically from API call
    suspend fun getDrivers(): List<DriverModel?>?

    suspend fun getRoutes():List<RouteModel?>?


    //Store drivers in DB after API call
    suspend fun fetchAndStoreDrivers()
    suspend fun fetchAndStoreRoutes()

    //Retrieve drivers and routes specifically from DB
    suspend fun getStoredDrivers(): List<DriverEntity>
    suspend fun getStoredRoutes(): List<RouteEntity>

}