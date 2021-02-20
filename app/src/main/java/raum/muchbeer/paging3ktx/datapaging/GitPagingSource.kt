package raum.muchbeer.paging3ktx.datapaging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import raum.muchbeer.paging3ktx.api.GitService
import raum.muchbeer.paging3ktx.api.IN_QUALIFIER
import raum.muchbeer.paging3ktx.model.Repo
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1
class GitPagingSource(
    private val service: GitService,
    private val query: String
) : PagingSource<Int, Repo>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        val apiQuery = query + IN_QUALIFIER

        return try {
            val response = service.searchRepos(apiQuery, position, params.loadSize)
            val repos = response.items
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return null
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }

}