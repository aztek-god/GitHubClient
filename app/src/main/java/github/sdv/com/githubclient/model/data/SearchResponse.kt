package github.sdv.com.githubclient.model.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<UserInfo>?,
    @SerializedName("total_count")
    val totalCount: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.createTypedArrayList(UserInfo),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(incompleteResults)
        parcel.writeTypedList(items)
        parcel.writeValue(totalCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchResponse> {
        override fun createFromParcel(parcel: Parcel): SearchResponse {
            return SearchResponse(parcel)
        }

        override fun newArray(size: Int): Array<SearchResponse?> {
            return arrayOfNulls(size)
        }
    }
}