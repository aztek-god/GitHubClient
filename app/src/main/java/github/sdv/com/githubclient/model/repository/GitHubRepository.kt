package github.sdv.com.githubclient.model.repository

import github.sdv.com.githubclient.model.data.SearchResponse
import github.sdv.com.githubclient.model.data.UserInfo
import github.sdv.com.githubclient.onBackground
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class GitHubRepository(private val retrofit: Retrofit) {

    private val mService: Service by lazy { retrofit.create(Service::class.java) }

    interface Service {
        @GET("users")
        fun getUsers(): Observable<List<UserInfo>>

        @GET("search/users")
        fun searchUsers(@Query("q") query: String): Observable<SearchResponse>
    }


    fun search(searchQuery: String): Observable<SearchResponse> {
        return mService
            .searchUsers(searchQuery)
            .onBackground()
    }

    fun getAllUsers(): Observable<List<UserInfo>> {
        return mService
            .getUsers()
            .onBackground()
    }
}