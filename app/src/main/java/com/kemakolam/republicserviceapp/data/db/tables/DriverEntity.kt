package com.kemakolam.republicserviceapp.data.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DriverEntity(

    @PrimaryKey(autoGenerate = true) val pKey: Int = 0, //auto increment primary key
    val id: String? = "",
    val name:String?,
)