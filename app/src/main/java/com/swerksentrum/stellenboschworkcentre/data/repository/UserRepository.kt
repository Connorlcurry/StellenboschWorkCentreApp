package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.UserDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.User

class UserRepository (

    private val userDao: UserDao

) {

    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun getUserById(userId: Int): User? = userDao.getUserById(userId)

    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)

    suspend fun insertUser(user: User): Long = userDao.addUser(user)

    suspend fun updateUser(user: User) = userDao.updateUser(user)

    suspend fun deleteUser(userId: Int) = userDao.deleteUser(userId)

}