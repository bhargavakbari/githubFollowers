package com.android.githubfollowersapp.dataRepo.localDataBase.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.android.githubfollowersapp.dataRepo.localDataBase.daos.GitHubUserDAO
import com.android.githubfollowersapp.dataRepo.localDataBase.entities.GitHubUserEntity

@Database(entities = [GitHubUserEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        private val DB_NAME = "local_database.db"
        @Volatile
        private var instance: AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppDataBase? {
            if (instance == null) {
                instance = create(context)
            }
            return instance
        }

        private fun create(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java!!, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    abstract fun getFollowersDao(): GitHubUserDAO

}