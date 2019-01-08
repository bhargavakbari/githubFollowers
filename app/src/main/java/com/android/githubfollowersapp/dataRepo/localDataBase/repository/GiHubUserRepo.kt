package com.android.githubfollowersapp.dataRepo.localDataBase.repository

import android.os.AsyncTask
import com.android.githubfollowersapp.dataRepo.localDataBase.database.AppDataBase
import com.android.githubfollowersapp.dataRepo.localDataBase.entities.GitHubUserEntity
import java.util.ArrayList

class GiHubUserRepo {

    companion object {
        fun insertCategoryList(mDb: AppDataBase?, gitHubUserEntity: GitHubUserEntity) {
            val task = PopulateDbAsync(mDb, gitHubUserEntity)
            task.execute()
        }
    }

    private class PopulateDbAsync internal constructor(val mDb: AppDataBase?,
                                                       val gitHubUserEntity: GitHubUserEntity) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void): Void? {
            mDb?.getFollowersDao()?.insertFollowersList(gitHubUserEntity)
            return null
        }

    }
}