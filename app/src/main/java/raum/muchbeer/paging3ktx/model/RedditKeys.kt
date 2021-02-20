package raum.muchbeer.paging3ktx.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "redditKey")
data class RedditKeys(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val after: String?,
    val before: String?
) {
}