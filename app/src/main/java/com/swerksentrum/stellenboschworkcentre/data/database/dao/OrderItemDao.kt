package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.OrderItem

@Dao
interface OrderItemDao {

    @Query("SELECT * FROM order_items WHERE orderID = :orderID")
    fun getItemsByOrder(orderID: Int): Flow<List<OrderItem>> // Fetches all the products of a specific order

    @Insert
    suspend fun addOrderItem(orderItem: OrderItem) // Add a product to the order

    @Query("DELETE FROM order_items WHERE orderID = :orderID")
    suspend fun deleteOrderItem(orderID: OrderItem) // Deletes a product from an order

}
