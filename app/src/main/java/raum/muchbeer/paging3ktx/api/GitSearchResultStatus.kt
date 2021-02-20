package raum.muchbeer.paging3ktx.api

import raum.muchbeer.paging3ktx.model.Repo

sealed class GitSearchResultStatus {
    data class Success(val data: List<Repo>) : GitSearchResultStatus()
    data class Error(val error: Exception) : GitSearchResultStatus()
}