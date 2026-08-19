package com.swerksentrum.stellenboschworkcentre.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "service_requests")

data class ServiceRequest(

    @PrimaryKey(autoGenerate = true)
    val requestID: Int = 0,
    val userID: Int, // Foreign key to User
    val serviceID: Int, // Foreign key to Service
    val status: String

)
