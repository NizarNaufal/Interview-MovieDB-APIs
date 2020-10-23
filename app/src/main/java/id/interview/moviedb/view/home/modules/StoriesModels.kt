package id.interview.moviedb.view.home.modules

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoriesModels(
    @SerializedName("id")  var id_stories: String?,
    @SerializedName("name") var name_stories: String?,
    @SerializedName("description") var desc_stories: String?,
    @SerializedName("url")  var url_image_stories: String?,
    @SerializedName("category")  var category_news: String?,
    @SerializedName("language") val language:String?,
    @SerializedName("country") val country_news:String?
) : Parcelable