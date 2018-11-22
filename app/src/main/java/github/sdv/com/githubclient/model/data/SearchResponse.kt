package github.sdv.com.githubclient.model.data

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<UserInfo>?,
    @SerializedName("total_count")
    val totalCount: Int?
)