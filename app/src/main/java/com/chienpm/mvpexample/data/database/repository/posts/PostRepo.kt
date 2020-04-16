package com.chienpm.mvpexample.data.database.repository.posts

interface PostRepo {
    suspend fun loadPosts(): List<Post>
    suspend fun savePosts(posts: List<Post>)

}
