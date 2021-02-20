package raum.muchbeer.paging3ktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import raum.muchbeer.paging3ktx.adapter.RedditAdapter
import raum.muchbeer.paging3ktx.adapter.RedditLoadingAdapter
import raum.muchbeer.paging3ktx.viewmodel.RedditViewModel

class RedditActivity : AppCompatActivity() {

    private val redditAdapter = RedditAdapter()
    private val redditViewModel: RedditViewModel by lazy {
        ViewModelProvider(this).get(RedditViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        fetchPosts()
    }

    private fun fetchPosts() {
        lifecycleScope.launch {
            redditViewModel.fetchPosts().collectLatest { pagingData ->
                redditAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupViews() {
        recyclerView.adapter = redditAdapter
        recyclerView.adapter = redditAdapter.withLoadStateHeaderAndFooter(
            header = RedditLoadingAdapter { redditAdapter.retry() },
            footer = RedditLoadingAdapter { redditAdapter.retry() }
        )
    }
}