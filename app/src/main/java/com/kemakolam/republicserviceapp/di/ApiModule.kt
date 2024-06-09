package com.kemakolam.republicserviceapp.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.kemakolam.republicserviceapp.data.network.api.ApiDetails
import com.kemakolam.republicserviceapp.data.network.api.ApiEndpoints
import com.kemakolam.republicserviceapp.data.repository.ApiRepository
import com.kemakolam.republicserviceapp.data.repository.ApiRepositoryImpl
import com.google.gson.Gson
import com.kemakolam.republicserviceapp.data.db.MyDatabase
import com.kemakolam.republicserviceapp.data.db.dao.DriversDao
import com.kemakolam.republicserviceapp.data.db.dao.RoutesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

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
    fun providesApiEndpoints(retrofit: Retrofit): ApiEndpoints {
        return retrofit.create(ApiEndpoints::class.java)
    }

    //Provide DB Dependencies
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): MyDatabase {
        return Room.databaseBuilder(
            appContext,
            MyDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideDriverDao(database: MyDatabase): DriversDao {
        return database.driversDao()
    }
    @Provides
    fun provideRouteDao(database: MyDatabase): RoutesDao {
        return database.routesDao()
    }

    @Provides
    fun provideViewModelFactory(factory: HiltViewModelFactory): ViewModelProvider.Factory {
        return factory
    }

    @Provides
    fun providesRepository(apiEndpoints: ApiEndpoints, driverDao: DriversDao, routesDao: RoutesDao): ApiRepository {
        return ApiRepositoryImpl(apiEndpoints, driverDao, routesDao)
    }
}