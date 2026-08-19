package com.swerksentrum.stellenboschworkcentre.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "donations")

data class Donation(

    @PrimaryKey(autoGenerate = true)
    val donationID: Int = 0,
    val userID: Int, // Foreign key to User
    val amount: Double,
    val Purpose: String

)
