package github.sdv.com.githubclient.ui.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import github.sdv.com.githubclient.R
import github.sdv.com.githubclient.databinding.ActivityDetailBinding
import github.sdv.com.githubclient.model.data.UserInfo

class DetailActivity : AppCompatActivity() {

    private val mUserInfo: UserInfo? by lazy {
        intent?.let {
            it.extras?.getParcelable<UserInfo>(USER_INFO)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.userInfo = mUserInfo
        binding.executePendingBindings()
    }

    companion object {
        private const val USER_INFO = "userInfo"

        fun startActivity(context: Context, userInfo: UserInfo) {
            val intent: Intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(USER_INFO, userInfo)
            }

            context.startActivity(intent)
        }
    }
}
