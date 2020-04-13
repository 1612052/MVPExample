package com.chienpm.mvpexample.ui.posts

import com.chienpm.mvpdagger2retrofittypecode.ui.base.BaseContract
import com.chienpm.mvpexample.database.repository.posts.Post

class PostContract {
    interface View: BaseContract.View{
        fun showProgress()
        fun hideProgress()

        fun setItems(items: List<Post>)
        fun showEmptyView(message: String)

        fun navigatePostDetail(post: Post)
    }

    interface Presenter: BaseContract.Presenter<View>{

        fun loadPosts()

        fun onPostsLoaded(posts: List<Post>)

        fun setEmptyView()

        fun onItemClicked(item: Post)

    }

}
