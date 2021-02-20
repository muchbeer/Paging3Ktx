package raum.muchbeer.paging3ktx.repository.reddit

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.paging3ktx.api.RedditInstance
import raum.muchbeer.paging3ktx.api.RedditService
import raum.muchbeer.paging3ktx.db.RedditDatabase
import raum.muchbeer.paging3ktx.model.RedditPost

class RedditRepository(context: Context)  {

    private val redditService = RedditInstance.getClient().create(RedditService::class.java)
    private val redditDatabase = RedditDatabase.create(context)

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPost() : Flow<PagingData<RedditPost>> {

        return  Pager(
            PagingConfig(pageSize = 40, enablePlaceholders = false, prefetchDistance = 3),
            remoteMediator = RedditRemoteMediator(redditService, redditDatabase),
            pagingSourceFactory = { redditDatabase.redditPostsDao().getPosts() }
        ).flow

    }

}