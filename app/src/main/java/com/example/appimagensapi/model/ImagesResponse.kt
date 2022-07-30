package com.example.appimagensapi.model

import com.google.gson.annotations.SerializedName

//Chave "data" principal da api
data class ImagesResponse(
    val cats: List<ImagesDto>
    )
