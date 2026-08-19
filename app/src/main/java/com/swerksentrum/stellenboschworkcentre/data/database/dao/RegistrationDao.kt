package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Registration

@Dao
interface RegistrationDao {

    @Query("SELECT * FROM registrations WHERE userID = :userID")
    fun getRegistrationByUser(userID: Int): Flow<List<Registration>> // Fetches a registration record based on a userID

    @Query("SELECT * FROM registrations")
    fun getAllRegistrations(): Flow<List<Registration>> // Fetches all records from the registrations table

    @Insert
    suspend fun addRegistration(registration: Registration): Long // Inserts a new registration record

    @Update
    suspend fun updateRegistration(registration: Registration) // Updates a registration record

    @Query("UPDATE registrations SET status = :status WHERE registrationID = :registrationID")
    suspend fun updateRegistrationStatus(registrationID: Int, status: String) // Updates the status of a registration

}
