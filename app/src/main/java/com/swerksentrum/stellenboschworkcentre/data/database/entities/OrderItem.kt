package com.swerksentrum.stellenboschworkcentre.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_items")

data class OrderItem(

    @PrimaryKey(autoGenerate = true)
    val orderItemID: Int = 0,
    val orderID: Int, // Foreign key from Order
    val productID: Int, // Foreign key from Product
    val quantity: Int

)
