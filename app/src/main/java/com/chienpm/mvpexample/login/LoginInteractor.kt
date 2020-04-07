package com.chienpm.mvpexample.login

import com.chienpm.mvpexample.postDelayed

class LoginInteractor {

    interface OnLoginFinishedListener {
        fun onUsernameError()
        fun onPasswordError()
        fun onSuccess()
    }

    fun login(
        username: String,
        password: String,
        listener: OnLoginFinishedListener
    ) {
        when {
            username.isEmpty() -> listener.onUsernameError()
            password.isEmpty() -> listener.onPasswordError()
            else -> postDelayed(2000) {
                listener.onSuccess()
            }
        }
    }
}
