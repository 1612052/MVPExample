package com.chienpm.mvpexample.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chienpm.mvpexample.data.database.repository.comments.Comment
import com.chienpm.mvpexample.data.database.repository.comments.CommentsDao
import com.chienpm.mvpexample.data.database.repository.posts.Post
import com.chienpm.mvpexample.data.database.repository.posts.PostsDao
import com.chienpm.mvpexample.util.AppConstants

/**
 * https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#6
 */
@Database(entities = [Post::class, Comment::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postsDao(): PostsDao

    abstract fun commentsDao(): CommentsDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppConstants.APP_DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}