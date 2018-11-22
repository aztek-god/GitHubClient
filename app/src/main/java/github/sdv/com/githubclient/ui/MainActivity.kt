package github.sdv.com.githubclient.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import github.sdv.com.githubclient.R
import github.sdv.com.githubclient.adapter.MainAdapter
import github.sdv.com.githubclient.databinding.ActivityMainBinding
import github.sdv.com.githubclient.model.UserInfo
import github.sdv.com.githubclient.network.RetrofitFactory
import github.sdv.com.githubclient.network.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val mAdapter: MainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding.recyclerView) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

        RetrofitFactory
            .provideRetrofit(RetrofitFactory.provideHttp())
            .create(Service::class.java)
            .searchUsers("hero")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnNext {
                mAdapter.addData(it.items ?: emptyList<UserInfo>())
            }
            .doOnError {
                Log.d("tag1", "error = $it")
            }
            .subscribe()
    }
}
