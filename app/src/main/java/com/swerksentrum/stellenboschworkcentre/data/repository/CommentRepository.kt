package com.swerksentrum.stellenboschworkcentre.data.repository

import kotlinx.coroutines.flow.Flow
import com.swerksentrum.stellenboschworkcentre.data.database.dao.CommentDao
import com.swerksentrum.stellenboschworkcentre.data.database.entities.Comment

class CommentRepository(

    private val commentDao: CommentDao

) {

    fun getAllComments(): Flow<List<Comment>> = commentDao.getAllComments()

    fun getCommentsByUser(userID: Int): Flow<List<Comment>> = commentDao.getCommentsByUser(userID)

    suspend fun addComment(comment: Comment): Long = commentDao.addComment(comment)

}