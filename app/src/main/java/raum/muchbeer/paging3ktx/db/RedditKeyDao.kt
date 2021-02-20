package raum.muchbeer.paging3ktx.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import raum.muchbeer.paging3ktx.model.RedditKeys

@Dao
interface RedditKeyDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveRedditKeys(redditKey: RedditKeys)

    @Query("SELECT * FROM redditKey ORDER BY id DESC")
    suspend fun getRedditKeys(): List<RedditKeys>

}