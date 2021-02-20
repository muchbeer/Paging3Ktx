package raum.muchbeer.paging3ktx.repository.reddit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import raum.muchbeer.paging3ktx.api.RedditService
import raum.muchbeer.paging3ktx.model.RedditPost
import retrofit2.HttpException
import java.io.IOException

class RedditPagingDataSource(val redditService: RedditService) : PagingSource<String, RedditPost>() {



   override fun getRefreshKey(state: PagingState<String, RedditPost>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        return try {
            val response = redditService.fetchPosts(loadSize = params.loadSize)
            val listing = response.body()?.data
            val redditPosts = listing?.children?.map { it.data }
            //LoadResult.Page(data, prevKey, nextKey)
            LoadResult.Page(
                redditPosts ?: listOf(),
                listing?.before,
                listing?.after
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override val keyReuseSupported: Boolean = true

}