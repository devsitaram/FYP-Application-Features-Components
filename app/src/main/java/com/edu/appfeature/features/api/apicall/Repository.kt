package com.edu.appfeature.features.api.apicall

import com.edu.appfeature.features.api.displaypage.pojo.GameItems
import retrofit2.Call

class Repository(private val apiService: ApiServices?) {
    fun getGameList(): Call<List<GameItems>>? {
        return apiService?.getGames()
    }
}