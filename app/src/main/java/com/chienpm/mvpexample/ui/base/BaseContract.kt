package com.chienpm.mvpdagger2retrofittypecode.ui.base

class BaseContract{
    interface Presenter<in T>{
        fun subscribe()
        fun unsubscribe()

//        fun attach(view: T)
//        fun detach()
    }

    interface View{
    }
}