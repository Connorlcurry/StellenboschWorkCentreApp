package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>> // Fetches all the product records in the products table

    @Query("SELECT * FROM products WHERE productID = :productID")
    suspend fun getProductById(productID: Int): Flow<List<Product>> // Fetches a specific product

    @Query("SELECT * FROM products WHERE stockQuantity > 0")
    suspend fun getAvailableProducts(): Flow<List<Product>> // Fetches all records of available stock

    @Insert
    suspend fun addProduct(product: Product): Long // Inserts a new product record in products

    @Update
    suspend fun updateProduct(product: Product) // Updates a product records

    @Query("UPDATE products SET stockQuantity = stockQuantity - :quantity WHERE productID = :productID")
    suspend fun reduceStock(productID: Int, quantity: Int) // Updates a records by reducing the stock of that product

}
