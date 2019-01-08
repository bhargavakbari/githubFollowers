package com.android.githubfollowersapp.dataRepo.localDataBase.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "FollowersTable")
data class GitHubUserEntity(var userName: String, var followersListJsonStr: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}