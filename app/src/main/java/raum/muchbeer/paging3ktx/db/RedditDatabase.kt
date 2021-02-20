package raum.muchbeer.paging3ktx.db

import android.content.Context
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import raum.muchbeer.paging3ktx.model.RedditKeys
import raum.muchbeer.paging3ktx.model.RedditPost

@Database(
  entities = [RedditPost::class, RedditKeys::class], version = 1, exportSchema = false
)
abstract class RedditDatabase : RoomDatabase()
{
    companion object {
        fun create(context: Context): RedditDatabase {
            val databaseBuilder =
                Room.databaseBuilder(context, RedditDatabase::class.java, "redditclone.db")
            return databaseBuilder.build()
        }
    }

    abstract fun redditPostsDao(): RedditDao
    abstract fun redditKeysDao(): RedditKeyDao

}