package com.chienpm.mvpexample.data.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiHelper {
    @GET(TYPICO_PROJECT+"/users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET(TYPICO_PROJECT+"/posts")
    suspend fun getPosts(): Response<List<PostResponse>>


    @GET(TYPICO_PROJECT+"/comments")
    suspend fun getComments(): Response<List<CommentRespone>>

}