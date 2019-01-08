package com.android.githubfollowersapp.utility

import android.content.Context
import android.content.Intent
import com.android.githubfollowersapp.ui.activities.MainActivity

class IntentManager {
    companion object {
        fun startMainActivity(mContext: Context) {
            var mainActivity = Intent(mContext, MainActivity::class.java)
            mContext.startActivity(mainActivity)
        }
    }
}