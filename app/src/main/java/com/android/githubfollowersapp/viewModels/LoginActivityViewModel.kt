package com.android.githubfollowersapp.viewModels

import android.arch.lifecycle.ViewModel
import android.text.TextUtils

class LoginActivityViewModel : ViewModel() {

    fun isUserNameValid(mUserName: String): Boolean {
        return !TextUtils.isEmpty(mUserName) && mUserName.equals("ABC")
    }

    fun isPasswordValid(mPassword: String): Boolean {
        return !TextUtils.isEmpty(mPassword) && mPassword.equals("AAA")
    }
}