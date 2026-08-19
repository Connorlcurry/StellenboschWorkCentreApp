package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>> // Fetches all records from the users table

    @Query("SELECT * FROM users WHERE userID = :userID")
    suspend fun getUserById(userID: Int): User? // Fetches a user based on their userID

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User? // Fetches a user based on their email

    @Insert
    suspend fun addUser(user: User): Long // Inserts a new user record

    @Update
    suspend fun updateUser(user: User) // Updates a user record

    @Query("DELETE FROM users WHERE userID = :userID")
    suspend fun deleteUser(userID: Int) // Deletes a user from the users table

}
