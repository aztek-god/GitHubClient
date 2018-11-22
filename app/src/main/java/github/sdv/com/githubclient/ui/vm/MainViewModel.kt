package github.sdv.com.githubclient.ui.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import github.sdv.com.githubclient.model.data.UserInfo
import github.sdv.com.githubclient.model.repository.GitHubRepository

class MainViewModel(private val mRepository: GitHubRepository) : ViewModel() {

    private val mMutableUserLiveData: MutableLiveData<List<UserInfo>> = MutableLiveData()

    var isLoading = ObservableField<Boolean>(false)
    var isLoadingError = ObservableField<Boolean>(false)

    // hide live data mutability for encapsulation purposes.
    // This makes impossible to change mMutableUserLiveData field from outside
    // of MainViewModel class
    val userLiveData: LiveData<List<UserInfo>> get() = mMutableUserLiveData

    fun getAllUsers() {
//        isLoading.set(true)
        mRepository
            .getAllUsers()
            .doOnSubscribe {
                isLoading.set(true)
            }
            .doOnNext { userInfoList ->
                mMutableUserLiveData.value = userInfoList
            }
            .doOnError {
                isLoadingError.set(true)
            }
            .doOnComplete {
                isLoading.set(false)
            }
            .subscribe()
    }

    fun search(query: String) {
        mRepository.search(query)
            .map {
                it.items ?: emptyList()
            }
            .doOnNext { userInfoList ->
                mMutableUserLiveData.value = userInfoList
            }
            .doOnError {
                isLoadingError.set(true)
            }
            .doOnComplete {
//                isLoading.set(false)
            }
            .subscribe()
    }
}