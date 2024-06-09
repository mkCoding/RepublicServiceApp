package com.kemakolam.republicserviceapp.data.repository

import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import com.kemakolam.republicserviceapp.data.db.tables.RouteEntity
import com.kemakolam.republicserviceapp.data.network.model.DriverModel
import com.kemakolam.republicserviceapp.data.network.model.RouteModel

import com.kemakolam.republicserviceapp.ui.Route

interface ApiRepository {
    //Method to be implemented by ApiRepositoryImpl
    suspend fun getDrivers(): List<DriverModel?>?

    suspend fun getRoutes():List<RouteModel?>?


    //Store drivers in DB after API call
    suspend fun fetchAndStoreDrivers()
    suspend fun fetchAndStoreRoutes()

    //Retrieve stored drivers from DB
    suspend fun getStoredDrivers(): List<DriverEntity>
    suspend fun getStoredRoutes(): List<RouteEntity>

}