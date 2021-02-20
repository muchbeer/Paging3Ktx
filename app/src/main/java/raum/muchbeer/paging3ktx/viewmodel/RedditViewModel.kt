package raum.muchbeer.paging3ktx.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.paging3ktx.model.RedditPost
import raum.muchbeer.paging3ktx.repository.reddit.RedditRepository

class RedditViewModel(application: Application) : AndroidViewModel(application) {

    private val redditRepo = RedditRepository(application)

    fun fetchPosts(): Flow<PagingData<RedditPost>> {
        return redditRepo.fetchPost().cachedIn(viewModelScope)
    }


}