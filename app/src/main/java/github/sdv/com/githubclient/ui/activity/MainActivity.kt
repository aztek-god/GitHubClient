package github.sdv.com.githubclient.ui.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import github.sdv.com.githubclient.App
import github.sdv.com.githubclient.Constant
import github.sdv.com.githubclient.R
import github.sdv.com.githubclient.adapter.MainAdapter
import github.sdv.com.githubclient.asObservable
import github.sdv.com.githubclient.databinding.ActivityMainBinding
import github.sdv.com.githubclient.di.DaggerMainActivityComponent
import github.sdv.com.githubclient.ui.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val mAdapter: MainAdapter by lazy { MainAdapter() }

    @Inject
    lateinit var mModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerMainActivityComponent
            .builder()
            .appComponent((application as App).appComponent)
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mModel
        binding.executePendingBindings()

        with(binding.recyclerView) {
            adapter = mAdapter.apply {
                eventObservable
                    .doOnNext { userInfo ->
                        DetailActivity.startActivity(this@MainActivity, userInfo)
                    }
                    .subscribe()
            }
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

        searchView
            .asObservable()
            .filter { it != "" }
            .debounce(Constant.SEARCH_DELAY, TimeUnit.MILLISECONDS)
            .doOnNext {
                mModel.search(it)
            }
            .subscribe()


        mModel.userLiveData.observe(this, Observer {
            mAdapter.updateData(it ?: emptyList())
        })

        mModel.getAllUsers()
    }
}
