package com.chienpm.mvpexample.ui.posts

import com.chienpm.mvpexample.database.repository.posts.Post
import com.chienpm.mvpexample.ui.posts.interactor.PostInteractor
import com.chienpm.mvpexample.ui.posts.interactor.PostMvpInteractor

class PostPresenter(var postView: PostContract.View): PostContract.Presenter {

    private val interactor: PostMvpInteractor by lazy{
        PostInteractor.getInstance()
    }

    override fun onItemClicked(item: Post){
        postView.navigatePostDetail(item)
    }

    override fun loadPosts() {
        //todo do more
        interactor.loadPosts()
    }

    override fun onPostsLoaded(posts: List<Post>) {
        postView.apply {
            setItems(posts)
            hideProgress()
        }
    }

    override fun setEmptyView() {
        TODO("Not yet implemented")
    }


    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unsubscribe() {
        TODO("Not yet implemented")
    }
}
