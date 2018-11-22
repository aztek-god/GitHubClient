package github.sdv.com.githubclient.model.data

import android.databinding.BindingAdapter
import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("events_url")
    val eventsUrl: String?,
    @SerializedName("followers_url")
    val followersUrl: String?,
    @SerializedName("following_url")
    val followingUrl: String?,
    @SerializedName("gists_url")
    val gistsUrl: String?,
    @SerializedName("gravatar_id")
    val gravatarId: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("organizations_url")
    val organizationsUrl: String?,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String?,
    @SerializedName("repos_url")
    val reposUrl: String?,
    @SerializedName("site_admin")
    val siteAdmin: Boolean?,
    @SerializedName("starred_url")
    val starredUrl: String?,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatarUrl)
        parcel.writeString(eventsUrl)
        parcel.writeString(followersUrl)
        parcel.writeString(followingUrl)
        parcel.writeString(gistsUrl)
        parcel.writeString(gravatarId)
        parcel.writeString(htmlUrl)
        parcel.writeValue(id)
        parcel.writeString(login)
        parcel.writeString(nodeId)
        parcel.writeString(organizationsUrl)
        parcel.writeString(receivedEventsUrl)
        parcel.writeString(reposUrl)
        parcel.writeValue(siteAdmin)
        parcel.writeString(starredUrl)
        parcel.writeString(subscriptionsUrl)
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserInfo> {
        override fun createFromParcel(parcel: Parcel): UserInfo {
            return UserInfo(parcel)
        }

        override fun newArray(size: Int): Array<UserInfo?> {
            return arrayOfNulls(size)
        }

        @BindingAdapter("asyncLoad")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            Glide.with(imageView).load(url).into(imageView)
        }
    }
}