package com.kemakolam.republicserviceapp.data.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Route(
    @PrimaryKey (autoGenerate = true) val pId:Int, //auto-increment primary key
    val name:String?,
    val type:String?,
)