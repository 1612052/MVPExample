package com.chienpm.mvpexample.ui.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chienpm.mvpexample.R
import com.chienpm.mvpexample.data.database.repository.posts.Post
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity(), PostContract.View {

    lateinit var adapter: PostAdapter
    private val presenter: PostContract.Presenter by lazy{PostPresenter(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        reload.setOnClickListener{presenter.reload()}
        postList.layoutManager = LinearLayoutManager(this)
        presenter.loadPosts()
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun setItems(items: List<Post>) {
//        adapter = PostAdapter(items, presenter::onItemClicked)    // OR
        adapter = PostAdapter(items){
            presenter.onItemClicked(it)
        }
        postList.adapter = adapter
    }

    override fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    override fun hideEmptyView(){
        emptyView.visibility = View.GONE
    }

    override fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun navigatePostDetail(post: Post) {
        Toast.makeText(this, "clicked ${post.title}", Toast.LENGTH_LONG).show()
    }

}
