package com.android.githubfollowersapp.viewHolders

import android.content.Context
import android.view.View
import com.android.githubfollowersapp.pojo.GitHubUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_followers.view.*
import java.util.*

class FollowersViewHolder(view: View, val mContext: Context) : BaseViewHolder(view, mContext) {

    var followersAvatatImg = view.followersAvatarIv
    var followerNameTv = view.followerNameTv
    var followerIdTv = view.followerIdTv
    var followerTypeTv = view.followerTypeTv
    var followerSiteAdminTv = view.followerSiteAdminTv
    var followerOrganizationUrlTv = view.followerOrganizationUrlTv
    var followersList = ArrayList<GitHubUser>()


    override fun initDataForRecyclerView(items: List<Any>) {
        followersList = items as ArrayList<GitHubUser>
    }

    override fun currentItemPosition(position: Int) {
        super.currentItemPosition(position)
        Picasso.with(mContext).load(followersList!![adapterPosition].avatarUrl).into(followersAvatatImg)
        followerNameTv.text = followersList!![adapterPosition].login
        followerIdTv.text = followersList!![adapterPosition].id.toString()
        followerTypeTv.text = followersList!![adapterPosition].type
        followerSiteAdminTv.text = followersList!![adapterPosition].siteAdmin.toString()
        followerOrganizationUrlTv.text = followersList!![adapterPosition].organizationsUrl
    }
}