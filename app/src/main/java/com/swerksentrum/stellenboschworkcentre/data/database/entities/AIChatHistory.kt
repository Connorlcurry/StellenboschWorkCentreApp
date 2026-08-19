package com.swerksentrum.stellenboschworkcentre.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ai_chat_history")

data class AIChatHistory(

    @PrimaryKey(autoGenerate = true)
    val aiChatID: Int = 0,
    val userID: Int, // Foreign key to User
    val message: String,
    val response: String

)
