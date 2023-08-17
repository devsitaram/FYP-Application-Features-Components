package com.edu.appfeature.features.api.displaypage

import com.edu.appfeature.features.api.apicall.ApiCallInstance
import com.edu.appfeature.features.api.apicall.ApiServices
import com.edu.appfeature.features.api.apicall.Repository
import com.edu.appfeature.features.api.displaypage.pojo.GameItems
import retrofit2.Call

class GameModel {

    // call the api
    fun getGame(): Call<List<GameItems>>? {
        val apiService: ApiServices? = ApiCallInstance.getAPIServiceInstance()
        return Repository(apiService).getGameList()

//        val apiService: ApiServices? = ApiCallInstance.getAPIServiceInstance()
//        return apiService?.getGames()
    }
}