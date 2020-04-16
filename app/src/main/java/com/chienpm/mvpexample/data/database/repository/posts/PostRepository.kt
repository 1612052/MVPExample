package com.chienpm.mvpexample.data.database.repository.posts

class PostRepository constructor(private val postsDao: PostsDao): PostRepo {

    override suspend fun loadPosts(): List<Post> {
        return postsDao.loadAll()
    }

    override suspend fun savePosts(posts: List<Post>) {
        postsDao.insertAll(posts)
    }
}