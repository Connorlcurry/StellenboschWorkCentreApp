package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.DonationDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Donation

class DonationRepository(

    private val donationDao: DonationDao

) {

    fun getDonationsByUser(userId: Int): Flow<List<Donation>> = donationDao.getDonationsByUser(userId)

    fun getAllDonations(): Flow<List<Donation>> = donationDao.getAllDonations()

    suspend fun getTotalDonations(): Double = donationDao.getTotalDonations()

    suspend fun insertDonation(donation: Donation): Long = donationDao.addDonation(donation)

}