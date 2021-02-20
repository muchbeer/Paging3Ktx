package raum.muchbeer.paging3ktx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repo_state_item_status.view.*
import raum.muchbeer.paging3ktx.R

class ReposLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ReposLoadStateAdapter.ReposLoadVH>() {

    override fun onBindViewHolder(holder: ReposLoadVH, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ReposLoadVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.repo_state_item_status, parent, false)

        return ReposLoadVH(view, retry)
    }


    class ReposLoadVH(itemView: View, retry: () -> Unit) : RecyclerView.ViewHolder(itemView) {


        init {
            itemView.retry_button.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                itemView.error_msg.text = loadState.error.localizedMessage
            } else {
                itemView.progress_bar.isVisible = loadState is LoadState.Loading
                itemView.retry_button.isVisible = loadState !is LoadState.Loading
                itemView.error_msg.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}