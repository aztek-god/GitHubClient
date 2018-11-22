package github.sdv.com.githubclient.network

import github.sdv.com.githubclient.model.SearchResponse
import github.sdv.com.githubclient.model.UserInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface Service {
    @GET("users")
    fun getUsers(): Observable<List<UserInfo>>

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Observable<SearchResponse>
}