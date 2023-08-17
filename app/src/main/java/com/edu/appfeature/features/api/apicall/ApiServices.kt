package com.edu.appfeature.features.api.apicall

import com.edu.appfeature.features.api.displaypage.pojo.GameItems
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    // local server data
    @GET("games")
    fun getGames(): Call<List<GameItems>>
}