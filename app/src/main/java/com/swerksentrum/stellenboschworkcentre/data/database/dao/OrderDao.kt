package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Order

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders WHERE userID = :userID")
    fun getOrderByUser(userID: Int): Flow<List<Order>> // Fetches all the orders placed by a specific user

    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<Order>> // Fetches all orders

    @Insert
    suspend fun addOrder(order: Order): Long // Inserts a new order record

    @Update
    suspend fun updateOrder(order: Order) // Updates an order records in orders

}
