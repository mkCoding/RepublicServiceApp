package com.example.republicserviceapp.data.repository

import com.example.republicserviceapp.data.network.model.DriverModel

interface ApiRepository {
    //Method to be implemented by ApiRepositoryImpl
    suspend fun getDrivers(): DriverModel
}