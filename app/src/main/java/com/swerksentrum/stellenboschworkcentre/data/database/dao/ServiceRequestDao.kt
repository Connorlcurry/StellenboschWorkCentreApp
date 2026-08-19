package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.ServiceRequest

@Dao
interface ServiceRequestDao {

    @Query("SELECT * FROM service_requests WHERE userID = :userID")
    fun getServiceRequestByUser(userID: Int): Flow<List<ServiceRequest>> // Fetches the service requests of a specific user

    @Query("SELECT * FROM service_requests WHERE status = :status")
    fun getServiceRequestByStatus(status: String): Flow<List<ServiceRequest>> // Fetches all service requests of a specific status

    @Insert
    suspend fun addServiceRequest(request: ServiceRequest): Long // Inserts a service requests in service_requests

    @Update
    suspend fun updateServiceRequest(request: ServiceRequest) // Updates a service request record

}
