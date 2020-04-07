package com.chienpm.mvpexample

import android.os.Handler

fun postDelayed(delayMilis: Long, task:()->Unit){
    Handler().postDelayed(task, delayMilis)
}