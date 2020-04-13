package com.chienpm.mvpexample.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.chienpm.mvpexample.util.AppConstants

class AppPreferenceHelper constructor(
    context: Context,
    prefFileName: String
) : PreferenceHelper {

    companion object {
        private val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
    }

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)


    override fun getCurrentUserId(): Int? {
        val userId = mPrefs.getInt(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX)
        return when (userId) {
            AppConstants.NULL_INDEX -> null
            else -> userId
        }
    }

    override fun setCurrentUserId(userId: Int?) {
        val id = userId ?: AppConstants.NULL_INDEX

        val editor = mPrefs.edit()
        editor.putInt(PREF_KEY_CURRENT_USER_ID, id)
        editor.apply()


    }

    override fun getCurrentUserName(): String? = mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)

    override fun setCurrentUserName(userName: String?) {
        val editor = mPrefs.edit()
        editor.putString(PREF_KEY_CURRENT_USER_NAME, userName)
        editor.apply()

    }

    override fun getAccessToken(): String? = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)

    override fun setAccessToken(token: String?) {
        val editor = mPrefs.edit()
        editor.putString(PREF_KEY_ACCESS_TOKEN, token)
        editor.apply()

    }
}