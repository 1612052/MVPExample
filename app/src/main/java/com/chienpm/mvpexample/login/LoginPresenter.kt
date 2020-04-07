package com.chienpm.mvpexample.login

class LoginPresenter(
    var loginView: LoginView?,
    var listener: LoginInteractor
) : LoginInteractor.OnLoginFinishedListener {

    fun validateCridentials(username: String, password: String) {
        loginView?.showProgress()
        listener.login(username, password, this)
    }

    override fun onUsernameError() {
        loginView?.apply{
            setUsernameError()
            hideProgress()
        }
    }

    override fun onPasswordError() {
        loginView?.apply {
            setPasswordError()
            hideProgress()
        }
    }

    override fun onSuccess() {
        loginView?.apply{
            hideProgress()
            navigateToHome()
        }
    }

    fun onDestroy() {
        loginView = null
    }

}
