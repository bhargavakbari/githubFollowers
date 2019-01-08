package com.android.githubfollowersapp.dataRepo.localDataBase.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.android.githubfollowersapp.dataRepo.localDataBase.entities.GitHubUserEntity

@Dao
interface GitHubUserDAO {

    @Query("SELECT * FROM FollowersTable WHERE userName==:mUserName")
    fun getAllFollowersForParticularUser(mUserName: String): LiveData<GitHubUserEntity>

    @Insert
    fun insertFollowersList(followersList: GitHubUserEntity)

    @Query("DELETE FROM FollowersTable")
    fun nukeTable()

}