package com.chienpm.mvpexample.database.repository.comments

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface CommentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comments: List<Comment>)

    @Query("select * from comments")
    suspend fun loadAll(): List<Comment>
}
