package raum.muchbeer.paging3ktx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.paging3ktx.model.Repo
import raum.muchbeer.paging3ktx.repository.github.GithubRepository

@OptIn(ExperimentalPagingApi::class)
class GitSearchViewModel(private val repository: GithubRepository) : ViewModel() {
    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Repo>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<Repo>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Repo>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}