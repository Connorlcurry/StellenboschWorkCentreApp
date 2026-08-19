package com.swerksentrum.stellenboschworkcentre.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registrations")

data class Registration(

    @PrimaryKey(autoGenerate = true)
    val registrationID: Int = 0,
    val userID: Int, // Foreign key to User
    val supportType: String,
    val status: String

)
