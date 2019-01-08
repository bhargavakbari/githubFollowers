package com.android.githubfollowersapp.ui.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.android.githubfollowersapp.R
import com.android.githubfollowersapp.adapters.GenericAdapter
import com.android.githubfollowersapp.dataRepo.localDataBase.database.AppDataBase
import com.android.githubfollowersapp.dataRepo.localDataBase.entities.GitHubUserEntity
import com.android.githubfollowersapp.pojo.GitHubUser
import com.android.githubfollowersapp.utility.SpacesItemDecoration
import com.android.githubfollowersapp.viewHolders.FollowersViewHolder
import com.android.githubfollowersapp.viewModels.MainActivityViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_layout.*


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val SPACE = 15
    lateinit var mMainActivityViewModel: MainActivityViewModel
    var mDb: AppDataBase? = null
    var adapter: GenericAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDb = AppDataBase.getInstance(this)
        mMainActivityViewModel = MainActivityViewModel(mDb)
        configRecyclerView()
        showFollowersBtn.setOnClickListener {
            if (mMainActivityViewModel.isFollowersNameEmpty(etFollowersName.text.toString()))
                observeFollowersListData(etFollowersName.text.toString())
        }
    }

    private fun configRecyclerView() {
        followersRecyclerView.addItemDecoration(SpacesItemDecoration(SpacesItemDecoration.FOLLOWERS_TAG, SPACE))
        followersRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        }
    }

    fun observeFollowersListData(mFollowerName: String) {
        showProgressBar()
        checkIsFollowersListExistInDb(mFollowerName)
        val followersDataListObserver = Observer<List<GitHubUser>> { followersDataList ->
            if (followersDataList != null && followersDataList.size > 0) {
                Log.e(TAG, followersDataList.toString());
                setAdapter(followersDataList)
            } else {
                hideProgressBar()
                Toast.makeText(this, "No followers", Toast.LENGTH_SHORT).show()
                adapter?.refreshRecyclerView(ArrayList<GitHubUser>())
            }
        }
        mMainActivityViewModel.getFollowersList().observe(this, followersDataListObserver)
    }

    private fun checkIsFollowersListExistInDb(mFollowerName: String) {
        val followersDataObserver = Observer<GitHubUserEntity> { followersData ->
            val listType = object : TypeToken<List<GitHubUser>>() {}.type
            val followersDataList = Gson().fromJson<List<GitHubUser>>(followersData?.followersListJsonStr, listType)
            if (followersDataList != null && followersDataList.size > 0)
                setAdapter(followersDataList)
            else
                mMainActivityViewModel.getAllFollowersData(mFollowerName)
        }
        mDb?.getFollowersDao()?.getAllFollowersForParticularUser(mFollowerName)?.observe(this, followersDataObserver)
    }

    private fun setAdapter(followersDataList: List<GitHubUser>?) {
        hideProgressBar()
        followersRecyclerView?.let {
            adapter = GenericAdapter(followersDataList as List<Any>, this, R.layout.item_followers
                    , FollowersViewHolder::class.java.canonicalName)
            followersRecyclerView?.adapter = adapter
        }
    }

    private fun showProgressBar() {
        progressbarBaseLayout.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressbarBaseLayout.visibility = View.GONE
    }


}
