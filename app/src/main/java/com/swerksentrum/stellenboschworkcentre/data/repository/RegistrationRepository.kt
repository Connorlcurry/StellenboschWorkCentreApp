package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.RegistrationDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Registration

class RegistrationRepository(

    private val registrationDao: RegistrationDao

) {

    fun getRegistrationByUser(userID: Int): Flow<List<Registration>> = registrationDao.getRegistrationByUser(userID)

    fun getAllRegistrations(): Flow<List<Registration>> = registrationDao.getAllRegistrations()

    suspend fun addRegistration(registration: Registration): Long = registrationDao.addRegistration(registration)

    suspend fun updateRegistration(registration: Registration) = registrationDao.updateRegistration(registration)

    suspend fun updateRegistrationStatus(registrationID: Int, status: String) = registrationDao.updateRegistrationStatus(registrationID, status)

}