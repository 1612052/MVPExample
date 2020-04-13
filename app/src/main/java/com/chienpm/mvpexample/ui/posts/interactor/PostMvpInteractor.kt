package com.chienpm.mvpexample.ui.posts.interactor

import com.chienpm.mvpexample.database.repository.posts.Post
import com.chienpm.mvpexample.ui.posts.PostPresenter

interface PostMvpInteractor {

    fun subscribe(presenter: PostPresenter)
    fun unsubscribe(presenter: PostPresenter)

    fun loadPosts(): List<Post>
}