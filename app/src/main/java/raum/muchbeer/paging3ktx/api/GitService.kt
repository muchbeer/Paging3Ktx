package raum.muchbeer.paging3ktx.api

import raum.muchbeer.paging3ktx.model.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:name,description"

interface GitService {

    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoSearchResponse
}