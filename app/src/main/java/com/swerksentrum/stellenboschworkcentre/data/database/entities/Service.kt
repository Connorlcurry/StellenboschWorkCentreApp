package com.swerksentrum.stellenboschworkcentre.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "services")

data class Service(

    @PrimaryKey(autoGenerate = true)
    val serviceID: Int = 0,
    val serviceName: String,
    val Description: String

)
