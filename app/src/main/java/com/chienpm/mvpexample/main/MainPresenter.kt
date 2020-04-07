package com.chienpm.mvpexample.main

class MainPresenter(var mainView: MainView?,
                    var findItemsInteractor: FindItemsInteractor) {
    fun onResume(){
        mainView?.showProgress()
        findItemsInteractor.loadItems(::onItemsLoaded)
    }

    private fun onItemsLoaded(items: List<String>){
        mainView?.apply {
            setItems(items)
            hideProgress()
        }
    }

    fun onItemClicked(item: String){
        mainView?.showMessage(item)
    }

    fun onDestroy(){
        mainView = null
    }
}
