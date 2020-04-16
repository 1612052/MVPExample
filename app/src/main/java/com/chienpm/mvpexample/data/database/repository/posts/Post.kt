package com.chienpm.mvpexample.data.database.repository.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class Post(
//    @Expose
    @PrimaryKey
    var id: Int,

//    @Expose
    @SerializedName("user_id")
    @ColumnInfo(name="user_id")
    var userId: Int,

//    @Expose
    @SerializedName("title")
    @ColumnInfo(name="title")
    var title: String,

//    @Expose
    @SerializedName("body")
    @ColumnInfo(name = "body")
    var body: String
)