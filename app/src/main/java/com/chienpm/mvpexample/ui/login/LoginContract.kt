package com.chienpm.mvpexample.ui.login

import com.chienpm.mvpdagger2retrofittypecode.ui.base.BaseContract

class LoginContract{
    interface View: BaseContract.View{
        fun showProgress()
        fun hideProgress()
        fun setUsernameError()
        fun setPasswordError()
        fun setLoginSucceed(msg: String)
        fun setLoginFailed(reason: String)
        fun navigateToHome() // login succeed
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun onUsernameError()
        fun onPasswordError()
        fun onLoginSucceed(extraText: String)
        fun onLoginFailed(extraText: String)
        fun login(username: String, password: String)
    }
}