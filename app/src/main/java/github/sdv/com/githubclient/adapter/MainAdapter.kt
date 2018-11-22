package github.sdv.com.githubclient.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import github.sdv.com.githubclient.R
import github.sdv.com.githubclient.databinding.UserInfoLayoutBinding
import github.sdv.com.githubclient.model.data.UserInfo

class MainAdapter : RecyclerView.Adapter<MainAdapter.UserInfoViewHolder>() {

    private val mData: MutableList<UserInfo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserInfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemBinding: UserInfoLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.user_info_layout, parent, false)

        return UserInfoViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(vh: UserInfoViewHolder, index: Int) {
        vh.onBind(mData[index])
    }

    fun addData(data: List<UserInfo>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun updateData(data: List<UserInfo>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }


    class UserInfoViewHolder(private val binding: UserInfoLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(userInfo: UserInfo) {
            binding.userInfo = userInfo
        }
    }
}