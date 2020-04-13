package com.chienpm.mvpexample.database.repository.posts

class PostRepository constructor(private val postsDao: PostsDao): PostRepo {
    override suspend fun loadPosts(): List<Post> {
        return postsDao.loadAll()
    }
}