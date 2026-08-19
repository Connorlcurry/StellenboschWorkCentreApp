package com.swerksentrum.stellenboschworkcentre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Comment

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments ORDER BY commentID DESC")
    fun getAllComments(): Flow<List<Comment>> // Fetches all records from the comments table from newest to oldest

    @Query("SELECT * FROM comments WHERE userID = :userID")
    fun getCommentsByUser(userID: Int): Flow<List<Comment>> // Fetches the comments based on a userID

    @Insert
    suspend fun addComment(comment: Comment): Long // Adds a new comment records to comments

}
