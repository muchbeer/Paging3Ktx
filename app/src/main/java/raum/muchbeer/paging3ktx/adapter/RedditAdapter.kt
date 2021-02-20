package raum.muchbeer.paging3ktx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.reddit_item.view.*
import raum.muchbeer.paging3ktx.R
import raum.muchbeer.paging3ktx.model.RedditPost

class RedditAdapter : PagingDataAdapter<RedditPost, RedditAdapter.RedditViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reddit_item, parent, false)
        return RedditViewHolder(view)
    }


    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }



    class RedditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val scoreText: TextView = itemView.score
        private val commentsText: TextView = itemView.comments
        private val titleText: TextView = itemView.title

        fun bindPost(redditPost: RedditPost) {
            with(redditPost) {
                scoreText.text = score.toString()
                commentsText.text = commentCount.toString()
                titleText.text = title
            }
        }
    }

    companion object diffUtil : DiffUtil.ItemCallback<RedditPost>() {
        override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
            return oldItem.key == newItem.key
                    && oldItem.score == newItem.score
                    && oldItem.commentCount == newItem.commentCount
        }

    }
}