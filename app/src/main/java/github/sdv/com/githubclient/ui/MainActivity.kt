package github.sdv.com.githubclient.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import github.sdv.com.githubclient.App
import github.sdv.com.githubclient.R
import github.sdv.com.githubclient.adapter.MainAdapter
import github.sdv.com.githubclient.databinding.ActivityMainBinding
import github.sdv.com.githubclient.di.DaggerMainActivityComponent
import github.sdv.com.githubclient.model.repository.GitHubRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val mAdapter: MainAdapter by lazy { MainAdapter() }

    @Inject
    lateinit var mRepository: GitHubRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerMainActivityComponent
            .builder()
            .appComponent((application as App).appComponent)
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)


        Log.d("tag", "respository = $mRepository")

        mRepository
            .getAllUsers()
            .doOnNext {
                Log.d("tag", "response = $it")
            }
            .doOnError {
                Log.d("tag", "error = $it")
            }
            .subscribe()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding.recyclerView) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}
