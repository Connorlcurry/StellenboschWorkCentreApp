package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.AIChatHistory

@Dao
interface AIChatHistoryDao {

    @Query("SELECT * FROM ai_chat_history WHERE userID = :userID ORDER BY aiChatID DESC")
    fun getChatHistoryByUser(userID: Int): Flow<List<AIChatHistory>> // Fetches the chat history of a specific user from newest to oldest

    @Insert
    suspend fun addChatMessage(chat: AIChatHistory): Long // Insert a new chat message record

    @Query("DELETE FROM ai_chat_history WHERE userID = :userID")
    suspend fun clearChatHistory(userID: Int) // Deletes the chat history

}
