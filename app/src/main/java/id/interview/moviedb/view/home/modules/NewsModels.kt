package id.interview.moviedb.view.home.modules

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesModels(
    @SerializedName("source")  var source: ListSource?,
    @SerializedName("author")  var author_name: String?,
    @SerializedName("title") var title_news: String?,
    @SerializedName("description") var desc_news: String?,
    @SerializedName("url")  var url_image: String?,
    @SerializedName("urlToImage")  var url_to_image: String?,
    @SerializedName("publishedAt") val publishedAt:String?,
    @SerializedName("content") val content:String?
) : Parcelable

@Parcelize
data class ListSource(
    @SerializedName("id")  var id_list: String?,
    @SerializedName("name") var name: String?
) : Parcelable

