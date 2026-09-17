package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.OrderItemDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.OrderItem

class OrderItemRepository(

    private val orderItemDao: OrderItemDao

) {

    fun getItemsByOrder(orderID: Int): Flow<List<OrderItem>> = orderItemDao.getItemsByOrder(orderID)

    suspend fun addOrderItem(orderItem: OrderItem) = orderItemDao.addOrderItem(orderItem)

    suspend fun deleteOrderItem(orderID: Int) = orderItemDao.deleteOrderItem(orderID)

}