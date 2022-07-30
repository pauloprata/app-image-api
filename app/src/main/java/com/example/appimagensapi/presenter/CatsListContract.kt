package com.example.appimagensapi.presenter

import androidx.annotation.StringRes
import com.example.appimagensapi.model.ImagesDto
import com.example.appimagensapi.base.BaseContract

interface CatsListContract : BaseContract {

    interface Presenter : BaseContract.Presenter<View>{
        fun fetchRandomRecipes()
    }


    interface View: BaseContract.View{



        fun displayRecipes(list: List<ImagesDto>)

        fun displayLoading(isLoading: Boolean)

        fun showError(@StringRes message: Int)

    }

}