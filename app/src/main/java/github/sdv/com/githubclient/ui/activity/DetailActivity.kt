package github.sdv.com.githubclient.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import github.sdv.com.githubclient.R
import github.sdv.com.githubclient.model.data.UserInfo

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    companion object {
        fun startActivity(userInfo: UserInfo) {

        }
    }
}
