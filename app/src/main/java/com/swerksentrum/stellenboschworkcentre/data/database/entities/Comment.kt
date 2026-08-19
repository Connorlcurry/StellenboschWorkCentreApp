package com.swerksentrum.stellenboschworkcentre.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "comments")

data class Comment(

    @PrimaryKey(autoGenerate = true)
    val commentID: Int = 0,
    val userID: Int, // Foreign key to User
    val comment: String,
    val Rating: Int

)
