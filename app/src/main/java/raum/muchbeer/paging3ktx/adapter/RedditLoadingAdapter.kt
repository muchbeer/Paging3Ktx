package raum.muchbeer.paging3ktx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_loading_state.view.*
import raum.muchbeer.paging3ktx.R

class RedditLoadingAdapter(private val retry: () -> Unit) : LoadStateAdapter<RedditLoadingAdapter.RedditLoadingVH>() {


    override fun onBindViewHolder(holder: RedditLoadingVH, loadState: LoadState) {
       holder.bindState(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): RedditLoadingVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_loading_state, parent, false)
        return RedditLoadingVH(view, retry)
    }

    class RedditLoadingVH(itemView: View, retry: () -> Unit) : RecyclerView.ViewHolder(itemView){
        private val tvErrorMessage: TextView = itemView.tvErrorMessage
        private val progressBar: ProgressBar = itemView.progress_bar
        private val btnRetry: Button = itemView.btnRetry

        init {
            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }


        fun bindState(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                tvErrorMessage.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading

        }
    }
}