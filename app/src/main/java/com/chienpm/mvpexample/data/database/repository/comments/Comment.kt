package com.chienpm.mvpexample.data.database.repository.comments

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.chienpm.mvpexample.data.database.repository.posts.Post
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "comments"//,
//    foreignKeys = [ForeignKey(
//        entity = Post::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("post_id"),
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class Comment(
//    @Expose
    @PrimaryKey
    val id: Int,

//    @Expose
    @SerializedName("post_id")
    @ColumnInfo(name = "post_id")
    val postId: Int,

//    @Expose
    @SerializedName("body")
    @ColumnInfo(name = "body")
    val body: String
)