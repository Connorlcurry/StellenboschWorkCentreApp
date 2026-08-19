package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.ProductDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Product

class ProductRepository(

    private val productDao: ProductDao

) {

    fun getAllProducts(): Flow<List<Product>> = productDao.getAllProducts()

    suspend fun getProductById(productID: Int): Flow<List<Product>> = productDao.getProductById(productID)

    suspend fun getAvailableProducts(): Flow<List<Product>> = productDao.getAvailableProducts()

    suspend fun addProduct(product: Product): Long = productDao.addProduct(product)

    suspend fun updateProduct(product: Product) = productDao.updateProduct(product)

    suspend fun reduceStock(productID: Int, quantity: Int) = productDao.reduceStock(productID, quantity)

}