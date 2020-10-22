package id.interview.moviedb.view.home.adapter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChannelTVModels(
    @SerializedName("id")var id: String?,
    @SerializedName("credit_id")var credit_id: String?,
    @SerializedName("name")var name_serial_tv: String?,
    @SerializedName("gender")var gender_tv: String?,
    @SerializedName("profile_path")var img_profile_path: String?,
    @SerializedName("watch_point")var poin: String?,
    @SerializedName("share_point")var sharepoin: String?,
    @SerializedName("watched")var watched: Boolean?,
    @SerializedName("shared")var shared: Boolean?
) : Parcelable