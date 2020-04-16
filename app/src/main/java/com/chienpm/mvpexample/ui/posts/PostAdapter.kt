package com.chienpm.mvpexample.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chienpm.mvpexample.R
import com.chienpm.mvpexample.data.database.repository.posts.Post
import kotlinx.android.synthetic.main.post_item_view.view.*

class PostAdapter(private val posts: List<Post>, private val listener: (Post) -> Unit) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(post: Post, listener: (Post) -> Unit) = with(itemView) {
            title.text = post.title
            body.text = post.body
            setOnClickListener { listener(post) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_item_view, parent, false)
        )
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.onBind(posts[position], listener)

}
