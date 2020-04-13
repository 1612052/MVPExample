package com.chienpm.mvpexample.database.repository.posts

interface PostRepo {
    suspend fun loadPosts(): List<Post>
}
