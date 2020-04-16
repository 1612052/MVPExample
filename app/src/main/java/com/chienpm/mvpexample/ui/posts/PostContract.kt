package com.chienpm.mvpexample.ui.posts

import com.chienpm.mvpdagger2retrofittypecode.ui.base.BaseContract
import com.chienpm.mvpexample.data.database.repository.posts.Post

class PostContract {
    interface View: BaseContract.View{
        fun showProgress()
        fun hideProgress()

        fun setItems(items: List<Post>)
        fun showEmptyView()
        fun hideEmptyView()

        fun makeToast(msg: String)

        fun navigatePostDetail(post: Post)
    }

    interface Presenter: BaseContract.Presenter<View>{

        fun loadPosts()

        fun reload()

        fun onItemClicked(item: Post)

    }

}
