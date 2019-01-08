package com.android.githubfollowersapp.dataRepo.networkDataRepo.apiEndPointsProviders

import com.android.githubfollowersapp.pojo.GitHubUser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowersAPI {
    @GET("/users/{followerName}/followers")
    fun getFollowersList(@Path("followerName") followerName: String): Observable<List<GitHubUser>>
}