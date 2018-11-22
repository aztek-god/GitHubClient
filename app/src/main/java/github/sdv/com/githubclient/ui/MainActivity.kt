package github.sdv.com.githubclient.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import github.sdv.com.githubclient.R
import github.sdv.com.githubclient.databinding.ActivityMainBinding
import github.sdv.com.githubclient.network.RetrofitFactory
import github.sdv.com.githubclient.network.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        RetrofitFactory
            .provideRetrofit(RetrofitFactory.provideHttp())
            .create(Service::class.java)
            .searchUsers("hero")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnNext {
                binding.searchResponse = it

                Log.d("tag1", "response = $it")
            }
            .doOnError {
                Log.d("tag1", "error = $it")
            }
            .subscribe()
    }
}
