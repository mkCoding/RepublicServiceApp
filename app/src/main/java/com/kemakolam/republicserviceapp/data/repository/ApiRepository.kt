package com.kemakolam.republicserviceapp.data.repository

import com.kemakolam.republicserviceapp.data.network.model.DriverModel
import com.kemakolam.republicserviceapp.data.network.model.DriversModel
import com.kemakolam.republicserviceapp.data.network.model.RouteModel

interface ApiRepository {
    //Method to be implemented by ApiRepositoryImpl
    suspend fun getDrivers(): List<DriverModel?>?

    suspend fun getRoutes():List<RouteModel?>?
}