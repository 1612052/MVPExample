package com.chienpm.mvpexample.main

import com.chienpm.mvpexample.postDelayed

class FindItemsInteractor {
    fun loadItems(callback: (List<String>)->Unit){
        postDelayed(2000){
            callback(createArrayList())
        }
    }

    private fun createArrayList(): List<String> = (1..100).map{"Item $it"}
}