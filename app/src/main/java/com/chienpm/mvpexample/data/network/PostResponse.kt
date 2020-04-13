package com.chienpm.mvpexample.data.network

data class PostResponse (
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)