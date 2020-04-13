package com.chienpm.mvpexample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chienpm.mvpexample.database.repository.comments.Comment
import com.chienpm.mvpexample.database.repository.comments.CommentsDao
import com.chienpm.mvpexample.database.repository.posts.Post
import com.chienpm.mvpexample.database.repository.posts.PostsDao

@Database(entities = [Post::class, Comment::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postsDao(): PostsDao

    abstract fun commentsDao(): CommentsDao

}