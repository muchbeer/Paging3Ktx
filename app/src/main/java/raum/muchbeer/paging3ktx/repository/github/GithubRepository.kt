package raum.muchbeer.paging3ktx.repository.github

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.paging3ktx.api.GitService
import raum.muchbeer.paging3ktx.datapaging.GitPagingSource
import raum.muchbeer.paging3ktx.datapaging.GitPagingSource.Companion.NETWORK_PAGE_SIZE
import raum.muchbeer.paging3ktx.db.repo.RepoDatabase
import raum.muchbeer.paging3ktx.model.Repo

@OptIn(ExperimentalPagingApi::class)
class GithubRepository(  private val service: GitService,
                         private val database: RepoDatabase) {

    /**
     * Search repositories whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */

    fun getSearchResultStream(query: String): Flow<PagingData<Repo>> {
        Log.d("GithubRepository", "New query: $query")

        // appending '%' so we can allow other characters to be before and after the query string
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { database.repoDao().reposByName(dbQuery) }

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
                remoteMediator = GitRemoteMediator(
                        query,
                        service,
                        database
                ),
            pagingSourceFactory =pagingSourceFactory
        ).flow
    }

}