package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.ServiceRequestDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.ServiceRequest

class ServiceRequestRepository(

    private val serviceRequestDao: ServiceRequestDao

) {

    fun getServiceRequestByUser(userID: Int): Flow<List<ServiceRequest>> = serviceRequestDao.getServiceRequestByUser(userID)

    fun getServiceRequestByStatus(status: String): Flow<List<ServiceRequest>> = serviceRequestDao.getServiceRequestByStatus(status)

    suspend fun addServiceRequest(request: ServiceRequest): Long = serviceRequestDao.addServiceRequest(request)

    suspend fun updateServiceRequest(request: ServiceRequest) = serviceRequestDao.updateServiceRequest(request)

}