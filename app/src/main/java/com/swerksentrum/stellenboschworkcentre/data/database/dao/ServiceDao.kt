package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Service

@Dao
interface ServiceDao {

    @Query("SELECT * FROM services")
    fun getAllServices(): Flow<List<Service>> // Fetches all records from the services table

    @Query("SELECT * FROM services WHERE serviceID = :serviceID")
    suspend fun getServiceById(serviceID: Int): Service? // Fetches a service based on its serviceID

    @Insert
    suspend fun addService(service: Service): Long // Inserts a new service record

    @Query("DELETE FROM services WHERE serviceID = :serviceID")
    suspend fun deleteService(serviceID: Int) // Deletes a service from the services table

}
