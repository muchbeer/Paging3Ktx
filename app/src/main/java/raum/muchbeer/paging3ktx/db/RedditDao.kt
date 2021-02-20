package raum.muchbeer.paging3ktx.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import raum.muchbeer.paging3ktx.model.RedditPost

@Dao
interface RedditDao {
    @Insert(onConflict = REPLACE)
    suspend fun savePosts(redditPosts: List<RedditPost>)

    @Query("SELECT * FROM reddistPosts")
    fun getPosts(): PagingSource<Int, RedditPost>
}