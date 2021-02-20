package raum.muchbeer.paging3ktx.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "reddistPosts")
data class RedditPost(
    @SerializedName("name")
    @PrimaryKey
    val key: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("num_comments")
    val commentCount: Int
)
