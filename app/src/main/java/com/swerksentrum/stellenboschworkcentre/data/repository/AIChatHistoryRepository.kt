package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.AIChatHistoryDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.AIChatHistory

class AIChatHistoryRepository(

    private val aiChatHistoryDao: AIChatHistoryDao

) {

    fun getChatHistoryByUser(userID: Int): Flow<List<AIChatHistory>> = aiChatHistoryDao.getChatHistoryByUser(userID)

    suspend fun addChatMessage(chat: AIChatHistory): Long = aiChatHistoryDao.addChatMessage(chat)

    suspend fun clearChatHistory(userID: Int) = aiChatHistoryDao.clearChatHistory(userID)

}