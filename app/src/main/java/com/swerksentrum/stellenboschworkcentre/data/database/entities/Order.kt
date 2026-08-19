package com.swerksentrum.stellenboschworkcentre.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")

data class Order(

    @PrimaryKey(autoGenerate = true)
    val orderID: Int = 0,
    val userID: Int, // Foreign key to User
    val orderDate: String,
    val status: String


)
