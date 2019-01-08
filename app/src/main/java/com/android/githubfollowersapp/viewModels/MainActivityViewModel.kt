package com.android.githubfollowersapp.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import android.util.Log
import com.android.githubfollowersapp.dataRepo.localDataBase.database.AppDataBase
import com.android.githubfollowersapp.dataRepo.localDataBase.entities.GitHubUserEntity
import com.android.githubfollowersapp.dataRepo.localDataBase.repository.GiHubUserRepo
import com.android.githubfollowersapp.dataRepo.networkDataRepo.dataProviders.FollowersDataModel
import com.android.githubfollowersapp.pojo.GitHubUser
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel {

    private var TAG = "MainActivityViewModel"
    private var compositeDisposable: CompositeDisposable
    private var mFollowersDataModel: FollowersDataModel
    private var gitHubUserList: MutableLiveData<List<GitHubUser>> = MutableLiveData();
    private var mDb: AppDataBase? = null

    constructor(appDb: AppDataBase?) {
        mFollowersDataModel = FollowersDataModel()
        compositeDisposable = CompositeDisposable()
        mDb = appDb
    }

    fun isFollowersNameEmpty(mFollowersName: String): Boolean {
        return !TextUtils.isEmpty(mFollowersName)
    }

    fun getAllFollowersData(mFollowerName: String) {
        val disposable = mFollowersDataModel.getAllFollowersDataList(mFollowerName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(object : DisposableObserver<List<GitHubUser>>() {
                    override fun onComplete() {

                    }

                    override fun onNext(followersList: List<GitHubUser>) {
                        Log.d(TAG, "onNext() \n ${followersList}")
                        addFollowersListInDataBase(followersList, mFollowerName)
                    }

                    override fun onError(error: Throwable) {
                        Log.e(TAG, error.toString())
                        gitHubUserList.value = null
                    }
                })
        compositeDisposable.add(disposable);
    }


    private fun addFollowersListInDataBase(followersList: List<GitHubUser>, mFollowerName: String) {
        var gson = Gson()
        var followersListStr = gson.toJson(followersList)
        GiHubUserRepo.insertCategoryList(mDb, GitHubUserEntity(mFollowerName, followersListStr))
        gitHubUserList.value = followersList
    }

    fun getFollowersList(): MutableLiveData<List<GitHubUser>> {
        return gitHubUserList
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}