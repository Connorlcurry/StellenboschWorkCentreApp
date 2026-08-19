package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Donation

@Dao
interface DonationDao {

    @Query("SELECT * FROM donations WHERE userID = :userID")
    fun getDonationsByUser(userID: Int): Flow<List<Donation>> // Fetches the donation records based on a userID

    @Query("SELECT * FROM donations")
    fun getAllDonations(): Flow<List<Donation>> // Fetches all records from the donations table

    @Query("SELECT SUM(Amount) FROM donations")
    suspend fun getTotalDonations(): Double // Calculates the sum of all donations made to SWC

    @Insert
    suspend fun addDonation(donation: Donation): Long // Inserts a new donation record

}
