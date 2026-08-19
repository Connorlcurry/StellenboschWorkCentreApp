package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.OrderDao
import com.swerksentrum.stellenboschworkcentre.data.database.dao.OrderItemDao
import com.swerksentrum.stellenboschworkcentre.data.database.dao.ProductDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Order
import com.swerksentrum.stellenboschworkcentre.data.database.entities.OrderItem

class OrderRepository(

    private val orderDao: OrderDao,
    private val orderItemDao: OrderItemDao,
    private val productDao: ProductDao

) {

    fun getOrdersByUser(userId: Int): Flow<List<Order>> = orderDao.getOrderByUser(userId)

    fun getAllOrders(): Flow<List<Order>> = orderDao.getAllOrders()

    suspend fun createOrder(order: Order, items: List<OrderItem>): Long {

        // Insert the order
        val orderId = orderDao.addOrder(order)

        // Insert all order items
        items.forEach { item -> orderItemDao.addOrderItem(item.copy(orderID = orderId.toInt()))

        // Reduce stock
        productDao.reduceStock(item.productID, item.quantity)

        }

        return orderId

    }

    suspend fun updateOrder(order: Order) = orderDao.updateOrder(order)

    fun getOrderItems(orderId: Int): Flow<List<OrderItem>> = orderItemDao.getItemsByOrder(orderId)

}