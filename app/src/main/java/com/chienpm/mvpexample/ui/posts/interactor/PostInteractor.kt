package com.chienpm.mvpexample.ui.posts.interactor

import android.util.Log
import com.chienpm.mvpexample.MvpApp
import com.chienpm.mvpexample.data.network.ApiHelper
import com.chienpm.mvpexample.data.network.RetrofitService
import com.chienpm.mvpexample.data.network.UserResponse
import com.chienpm.mvpexample.data.preference.AppPreferenceHelper
import com.chienpm.mvpexample.data.preference.PreferenceHelper
import com.chienpm.mvpexample.database.repository.posts.Post
import com.chienpm.mvpexample.ui.login.LoginPresenter
import com.chienpm.mvpexample.ui.posts.PostPresenter
import com.chienpm.mvpexample.util.AppConstants
import kotlinx.coroutines.*
import java.lang.Exception

class PostInteractor private constructor(): PostMvpInteractor {
    private object Holder {
        val INSTANCE = PostInteractor()
    }

    companion object {
        @JvmStatic
        fun getInstance(): PostInteractor {
            return Holder.INSTANCE
        }
    }

    // todo: inject this
    private val apiService: ApiHelper by lazy { RetrofitService.createService(ApiHelper::class.java) }
    //todo: inject this
    private val preferenceHelper: PreferenceHelper by lazy { AppPreferenceHelper(MvpApp.instance.applicationContext, AppConstants.PREF_NAME) }

//    private val subscribers by lazy { HashSet<LoginPresenter>() }

    override fun subscribe(presenter: PostPresenter) {
//        if (!subscribers.contains(presenter)) {
//            subscribers.add(presenter)
//            Log.i("LoginManager", "Subscribe successful")
//        } else {
//            Log.i("LoginManager", "Subscribe unsuccessful: presenter is existed")
//        }
    }

    override fun unsubscribe(presenter: PostPresenter) {
//        if (subscribers.contains(presenter)) {
//            subscribers.remove(presenter)
//            Log.i("LoginManager", "Unsubscribe successful")
//        } else {
//            Log.i("LoginManager", "Unsubscribe unsuccessful: presenter is existed")
//        }
    }

    override fun loadPosts(): List<Post> {
        TODO("Not yet implemented")
    }
}