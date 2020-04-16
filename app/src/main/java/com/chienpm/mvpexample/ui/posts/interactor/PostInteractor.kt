package com.chienpm.mvpexample.ui.posts.interactor

import android.util.Log
import com.chienpm.mvpexample.MvpApp
import com.chienpm.mvpexample.data.database.AppDatabase
import com.chienpm.mvpexample.data.database.repository.posts.Post
import com.chienpm.mvpexample.data.database.repository.posts.PostRepo
import com.chienpm.mvpexample.data.database.repository.posts.PostRepository
import com.chienpm.mvpexample.data.network.ApiHelper
import com.chienpm.mvpexample.data.network.PostResponse
import com.chienpm.mvpexample.data.network.RetrofitService
import com.chienpm.mvpexample.ui.posts.PostPresenter
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.collections.HashSet

class PostInteractor private constructor() : PostMvpInteractor {
    private object Holder {
        val INSTANCE = PostInteractor()
    }

    companion object {
        @JvmStatic
        fun getInstance(): PostInteractor {
            return Holder.INSTANCE
        }
    }

    // todo: inject this
    private val apiService: ApiHelper by lazy { RetrofitService.createService(ApiHelper::class.java) }

    // todo: inject these
    private val postRepository: PostRepo by lazy {
        PostRepository(
            AppDatabase.getDatabase(
                MvpApp.instance.applicationContext
            ).postsDao()
        )
    }

    override fun loadPosts() {
        CoroutineScope(Dispatchers.Main).launch {
            // load from db
            val dpPosts = withContext(Dispatchers.IO) { postRepository.loadPosts() }

            Log.i("PostInteractor", "load from db")
            notifyDataUpdated(dpPosts, "load posts from db")

            // load from api
            val apiPosts = withContext(Dispatchers.IO) {
                val res = apiService.getPosts()
                parseResponse(res)
            }

            if(shouldUpdateDataFromApi(dpPosts, apiPosts)) {
                Log.i("PostInteractor", "load from api")
                notifyDataUpdated(apiPosts, "load from api")
                // save posts fetch from api to db
                withContext(Dispatchers.IO) { postRepository.savePosts(apiPosts) }
            }

        }
    }

    private fun shouldUpdateDataFromApi(dbPosts: List<Post>, apiPosts: List<Post>): Boolean {
        return apiPosts.size > dbPosts.size
    }

    private fun parseResponse(res: Response<List<PostResponse>>): List<Post> {
        if (!res.isSuccessful || res.body()?.isEmpty()!!) {
            return listOf()
        }
        // map PostResponse to Post
        return res.body()!!
            .map { p -> Post(id = p.id, title = p.title, body = p.body, userId = p.userId) }
    }

    fun notifyDataUpdated(
        posts: List<Post>,
        msg: String
    ) {
        subscribers.forEach {
            it.onDataUpdated(posts, msg)
        }
    }


    private val subscribers by lazy { HashSet<PostPresenter>() }

    override fun subscribe(presenter: PostPresenter) {
        if (!subscribers.contains(presenter)) {
            subscribers.add(presenter)
            Log.i("LoginManager", "Subscribe successful")
        } else {
            Log.i("LoginManager", "Subscribe unsuccessful: presenter is existed")
        }
    }

    override fun unsubscribe(presenter: PostPresenter) {
        if (subscribers.contains(presenter)) {
            subscribers.remove(presenter)
            Log.i("LoginManager", "Unsubscribe successful")
        } else {
            Log.i("LoginManager", "Unsubscribe unsuccessful: presenter is existed")
        }
    }

}