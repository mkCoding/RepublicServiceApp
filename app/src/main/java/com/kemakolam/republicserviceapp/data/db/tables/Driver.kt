package com.kemakolam.republicserviceapp.data.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Driver(
    @PrimaryKey (autoGenerate = true) val pId:Int, //auto increment primary key
    val id: String? = "",
    val name:String?,
)