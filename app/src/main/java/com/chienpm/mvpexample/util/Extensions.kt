package com.chienpm.mvpexample.util

import android.os.Handler

fun postDelayed(delayMilis: Long, task:()->Unit){
    Handler().postDelayed(task, delayMilis)
}