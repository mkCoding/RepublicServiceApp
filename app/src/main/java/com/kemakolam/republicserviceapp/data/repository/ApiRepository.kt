package com.kemakolam.republicserviceapp.data.repository

import com.kemakolam.republicserviceapp.data.network.model.DriverModel

interface ApiRepository {
    //Method to be implemented by ApiRepositoryImpl
    suspend fun getDrivers(): DriverModel
}