package com.android.githubfollowersapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.android.githubfollowersapp.R
import com.android.githubfollowersapp.utility.IntentManager
import com.android.githubfollowersapp.viewModels.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mLoginActivityViewModel: LoginActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener(this)
        mLoginActivityViewModel = LoginActivityViewModel()
    }

    override fun onClick(view: View) {
        if (view == btnLogin) {
            if (!mLoginActivityViewModel.isUserNameValid(etUserName.text.toString()))
                wrapperEtUserName.setError(getString(R.string.username_error))
            else if (!mLoginActivityViewModel.isPasswordValid(etPassword.text.toString()))
                wrapperEtPassword.setError(getString(R.string.password_error))
            else {
                IntentManager.startMainActivity(this)
                finish()
            }
        }
    }
}
