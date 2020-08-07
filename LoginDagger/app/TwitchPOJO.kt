
import com.google.gson.annotations.SerializedName

data class TwitchPOJO(
    @SerializedName("top")
    val top: List<Top>,
    @SerializedName("_total")
    val total: Int
)