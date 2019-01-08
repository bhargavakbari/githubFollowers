package com.android.githubfollowersapp.dataRepo.networkDataRepo.dataProviders

import com.android.githubfollowersapp.AppController
import com.android.githubfollowersapp.dataRepo.networkDataRepo.apiEndPointsProviders.FollowersAPI
import com.android.githubfollowersapp.pojo.GitHubUser
import io.reactivex.Observable

class FollowersDataModel {

    fun getAllFollowersDataList(mFollowerName: String): Observable<List<GitHubUser>> {
        val retrofit = AppController.getApplicationInstance().getRetrofitClient()
        val followerApi = retrofit.create(FollowersAPI::class.java)
        return followerApi.getFollowersList(mFollowerName)
    }

}