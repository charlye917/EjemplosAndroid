
import com.google.gson.annotations.SerializedName

data class Top(
    @SerializedName("channels")
    val channels: Int,
    @SerializedName("game")
    val game: Game,
    @SerializedName("viewers")
    val viewers: Int
)