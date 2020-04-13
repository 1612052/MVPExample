package com.chienpm.mvpexample.ui.login.interactor

import android.util.Log
import com.chienpm.mvpexample.MvpApp
import com.chienpm.mvpexample.data.network.ApiHelper
import com.chienpm.mvpexample.data.network.RetrofitService
import com.chienpm.mvpexample.data.network.UserResponse
import com.chienpm.mvpexample.data.preference.AppPreferenceHelper
import com.chienpm.mvpexample.data.preference.PreferenceHelper
import com.chienpm.mvpexample.ui.login.LoginPresenter
import com.chienpm.mvpexample.util.AppConstants
import kotlinx.coroutines.*
import java.lang.Exception

class LoginInteractor private constructor(): LoginMvpInteractor {
    private object Holder {
        val INSTANCE = LoginInteractor()
    }

    companion object {
        @JvmStatic
        fun getInstance(): LoginInteractor {
            return Holder.INSTANCE
        }
    }

    // todo: inject this
    private val apiService: ApiHelper by lazy { RetrofitService.createService(ApiHelper::class.java) }
    //todo: inject this
    private val preferenceHelper: PreferenceHelper by lazy { AppPreferenceHelper(MvpApp.instance.applicationContext, AppConstants.PREF_NAME) }

//    private val subscribers by lazy { HashSet<LoginPresenter>() }

    override fun subscribe(presenter: LoginPresenter) {
//        if (!subscribers.contains(presenter)) {
//            subscribers.add(presenter)
//            Log.i("LoginManager", "Subscribe successful")
//        } else {
//            Log.i("LoginManager", "Subscribe unsuccessful: presenter is existed")
//        }
    }

    override fun unsubscribe(presenter: LoginPresenter) {
//        if (subscribers.contains(presenter)) {
//            subscribers.remove(presenter)
//            Log.i("LoginManager", "Unsubscribe successful")
//        } else {
//            Log.i("LoginManager", "Unsubscribe unsuccessful: presenter is existed")
//        }
    }

    override fun login(username: String, password: String, callback: (UserResponse?, String) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val usersResponse = withContext(Dispatchers.IO) { apiService.getUsers() }

                val user = findUser(username, password, usersResponse.body())

                if (usersResponse.isSuccessful) {
                    if (user != null) {
                        callback(user, "Hello ${user.name}")
                        return@launch
                    } else {
                        callback(null, "Wrong username or password")
                    }
                } else {
                    callback(null, "Cannot connect to server")
                }
            } catch (e: Exception) {
                Log.e("LOGIN", "Exception ${e.message}")
                callback(null, "REQUEST ${e.message}")
            }
        }
    }

    override fun updateUserInSharedPref(user: UserResponse?) {
        preferenceHelper.let{
            it.setCurrentUserId(user?.id)
            it.setCurrentUserName(user?.name)
            it.setAccessToken("${user?.id}${user?.name}")
        }
    }

    private fun findUser(
        username: String,
        password: String,
        users: List<UserResponse>?
    ): UserResponse? {
        users?.forEach {
            if (it.username == username && it.password == password) {
                return it
            }
        }
        return null
    }
}