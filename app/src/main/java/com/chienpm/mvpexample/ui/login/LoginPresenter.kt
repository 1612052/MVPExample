package com.chienpm.mvpexample.ui.login

import com.chienpm.mvpexample.data.network.UserResponse
import com.chienpm.mvpexample.ui.login.interactor.LoginInteractor
import com.chienpm.mvpexample.ui.login.interactor.LoginMvpInteractor

class LoginPresenter(
    var loginView: LoginContract.View
) : LoginContract.Presenter {


    //todo: Inject this
    private val interactor: LoginMvpInteractor by lazy{LoginInteractor.getInstance()}

    override fun onUsernameError() {
        loginView.apply {
            setUsernameError()
            hideProgress()
        }
    }

    override fun onPasswordError() {
        loginView.apply {
            setPasswordError()
            hideProgress()
        }
    }

    override fun onLoginSucceed(extraText: String) {
        loginView.apply {
            hideProgress()
            setLoginSucceed(extraText)
            navigateToHome()
        }
    }

    override fun onLoginFailed(extraText: String) {
        loginView.apply {
            hideProgress()
            setLoginFailed(extraText)
        }
    }

    override fun login(username: String, password: String) {
        loginView.showProgress()
        when {
            username.isEmpty() -> onUsernameError()
            password.isEmpty() -> onPasswordError()
            else -> {
                doLoginRequest(username, password)
            }
        }
    }

    private fun doLoginRequest(username: String, password: String) {
        interactor.login(username, password) { loginResponse, extraText ->
            run {
                if (loginResponse != null) {
                    onLoginSucceed(extraText)
                    updateUserInSharedPref(loginResponse)
                } else {
                    onLoginFailed(extraText)
                }
            }
        }
    }


    override fun subscribe() {
        interactor.subscribe(this)
    }

    override fun unsubscribe() {
        interactor.unsubscribe(this)
    }


    private fun updateUserInSharedPref(loginResponse: UserResponse) {
        interactor.updateUserInSharedPref(loginResponse)
    }
}
