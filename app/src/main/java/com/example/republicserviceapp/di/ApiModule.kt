package com.example.republicserviceapp.di

import com.example.republicserviceapp.data.network.api.ApiDetails
import com.example.republicserviceapp.data.network.api.ApiEndpoints
import com.example.republicserviceapp.data.repository.ApiRepository
import com.example.republicserviceapp.data.repository.ApiRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides //<-- this will be dependency that we will inject as needed throughout the app

    //Create retrofit instance
    fun providesRetrofit():Retrofit{
        //used for converting objects to json
        val gson = Gson()
        val gsonConverterFactory = GsonConverterFactory.create(gson)

        //Create interceptor - log out all info to logcat whenever an API call is made
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        //create OKHttpClient
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun providesApiEndpoints(retrofit: Retrofit):ApiEndpoints{
        return retrofit.create(ApiEndpoints::class.java)
    }

    @Provides
    fun providesRepository(apiEndpoints: ApiEndpoints): ApiRepository{
        return ApiRepositoryImpl(apiEndpoints)
    }
}