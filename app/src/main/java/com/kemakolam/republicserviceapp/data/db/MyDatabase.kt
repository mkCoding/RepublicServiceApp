package com.kemakolam.republicserviceapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kemakolam.republicserviceapp.data.db.dao.DriversDao
import com.kemakolam.republicserviceapp.data.db.dao.RoutesDao
import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import com.kemakolam.republicserviceapp.data.db.tables.RouteEntity

@Database(
    entities = [DriverEntity::class, RouteEntity::class], //Specify the tables
    version = 1, //migrating to new DB version because of the new 'query' column added to Repo DB
    exportSchema = false
)

abstract class MyDatabase: RoomDatabase(){

    abstract fun driversDao(): DriversDao //method to access Drivers Dao
    abstract fun routesDao(): RoutesDao //method to access Routes Dao

    companion object{
        @Volatile
        private var INSTANCE:MyDatabase?=null //hold singleton instance of database
        fun getInstance(context:Context):MyDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database" //Name of your database file
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }





}