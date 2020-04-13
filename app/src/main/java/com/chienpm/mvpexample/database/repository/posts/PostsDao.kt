package com.chienpm.mvpexample.database.repository.posts

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chienpm.mvpexample.database.repository.comments.Comment

interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Query("select * from posts")
    suspend fun loadAll(): List<Post>
}
