package com.chienpm.mvpexample.ui.login.interactor

import com.chienpm.mvpexample.data.network.UserResponse
import com.chienpm.mvpexample.ui.login.LoginPresenter

interface LoginMvpInteractor {
    fun subscribe(presenter: LoginPresenter)
    fun unsubscribe(presenter: LoginPresenter)

    fun login(username: String, password: String, callback: (UserResponse?, String) -> Unit)
    fun updateUserInSharedPref(user: UserResponse?)
}