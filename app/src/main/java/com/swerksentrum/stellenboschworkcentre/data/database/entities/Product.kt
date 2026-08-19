package com.swerksentrum.stellenboschworkcentre.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")

data class Product(

    @PrimaryKey(autoGenerate = true)
    val productID: Int = 0,
    val prouctName: String,
    val price: Double,
    val stockQuantity: Int

)


