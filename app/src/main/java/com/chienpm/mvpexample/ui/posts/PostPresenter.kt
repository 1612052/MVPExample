package com.chienpm.mvpexample.ui.posts

import com.chienpm.mvpexample.data.database.repository.posts.Post
import com.chienpm.mvpexample.ui.posts.interactor.PostInteractor
import com.chienpm.mvpexample.ui.posts.interactor.PostMvpInteractor

class PostPresenter(var postView: PostContract.View) : PostContract.Presenter {

    //todo: inject this
    private val interactor: PostMvpInteractor by lazy {
        PostInteractor.getInstance()
    }

    override fun onItemClicked(item: Post) {
        postView.navigatePostDetail(item)
    }

    override fun loadPosts() {
        postView.showProgress()
        interactor.loadPosts()
    }

    override fun reload() {
        postView.showProgress()
        interactor.loadPosts() // sorry, Im lazy :[
    }

    fun onDataUpdated(
        posts: List<Post>,
        msg: String
    ) {
        postView.apply {
            hideProgress()
            setItems(posts)
            makeToast(msg)
            if (posts.isEmpty()) showEmptyView() else hideEmptyView()
        }
    }


    override fun subscribe() {
        interactor.subscribe(this)
    }

    override fun unsubscribe() {
        interactor.unsubscribe(this)
    }
}
