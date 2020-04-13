package com.chienpm.mvpexample.data.preference

interface PreferenceHelper {

    fun getCurrentUserId(): Int?
    fun setCurrentUserId(userId: Int?)

    fun getCurrentUserName(): String?
    fun setCurrentUserName(userName: String?)

    fun getAccessToken(): String?
    fun setAccessToken(token: String?)

}