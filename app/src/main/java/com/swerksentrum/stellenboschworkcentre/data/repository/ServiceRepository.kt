package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.ServiceDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Service

class ServiceRepository(

    private val serviceDao: ServiceDao

) {

    fun getAllServices(): Flow<List<Service>> = serviceDao.getAllServices()

    suspend fun getServiceById(serviceId: Int): Service? = serviceDao.getServiceById(serviceId)

    suspend fun insertService(service: Service): Long = serviceDao.addService(service)

    suspend fun deleteService(serviceId: Int) = serviceDao.deleteService(serviceId)

}