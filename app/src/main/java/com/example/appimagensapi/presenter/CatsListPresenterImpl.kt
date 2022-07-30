package com.example.appimagensapi.presenter

import com.example.appimagensapi.R
import com.example.appimagensapi.model.ImageService
import com.example.appimagensapi.model.NetWorkModule
import com.example.appimagensapi.base.LifecycleScope

import kotlinx.coroutines.launch
import java.lang.Exception

class CatsListPresenterImpl private constructor(private val service: ImageService)
    : CatsListContract.Presenter, LifecycleScope() {

    private var view: CatsListContract.View? = null

    override fun fetchRandomRecipes() {

        launch {
            view?.displayLoading(true)
            try {
                val response = service.getCats()

                view?.displayLoading(false)
                view?.displayRecipes(response.cats)

            }catch (exception: Exception){
                view?.displayLoading(false)
                view?.showError(R.string.error_message)
            }
        }

    }

    override fun attachView(view: CatsListContract.View) {
        this.view = view
    }

    override fun detachView() {
       this.view = null
    }

    companion object{
        fun create(service: ImageService = NetWorkModule.createNetworkService()): CatsListPresenterImpl {
            return CatsListPresenterImpl(service)
        }
    }

}
