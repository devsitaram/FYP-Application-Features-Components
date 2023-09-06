package com.edu.appfeature.features.searchpage

import androidx.lifecycle.ViewModel
import com.edu.appfeature.features.api.displaypage.GameModel
import com.edu.appfeature.features.api.displaypage.pojo.GameItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val gameModel = GameModel()
    private val onGameList = mutableListOf<GameItems>()

    private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _games = MutableStateFlow(onGameList.toList())
    val games = MutableStateFlow<List<GameItems>>(emptyList())

    init {
        fetchDataFromAPI()
    }

    private fun fetchDataFromAPI() {
        gameModel.getGame()?.enqueue(object : Callback<List<GameItems>> {
            override fun onResponse(
                call: Call<List<GameItems>>,
                response: Response<List<GameItems>>
            ) {
                if (response.isSuccessful) {
                    val apiData = response.body() ?: emptyList()
                    onGameList.clear()
                    onGameList.addAll(apiData)
                } else {
                    // error message

                }
            }

            override fun onFailure(call: Call<List<GameItems>>, throwable: Throwable) {
                throwable.printStackTrace()
            }
        })
    }

    fun onSearchingTextChange(text: String) {
        _searchText.value = text
        val filteredList = onGameList.filter { it -> it.title?.contains(text) ?: false }
        games.value = filteredList
    }
}

//fun GameItems.doesMatchSearchQuery(searchText: String?): Boolean {
//    val matchingCombinations = listOf(
//        title,
//        "${this.title?.first()}"
//    )
//    return matchingCombinations.any {
//        it?.contains(searchText ?: "", ignoreCase = true) ?: false
//    }
//}
//class SearchViewModel : ViewModel() {
//
//    private val _searchText = MutableStateFlow("")
//    val searchText = _searchText.asStateFlow()
//
//    private val _isSearching = MutableStateFlow(false)
//    val isSearching = _isSearching.asStateFlow()
//
//    // set list of data where get from api
//    private val _games = MutableStateFlow(allGames)
//    val games = searchText
//        .debounce(1000L)
//        .onEach { _isSearching.update { true } }
//        .combine(_games) { text, games ->
//            if (text.isBlank()) {
//                games
//            } else {
//                delay(2000L)
//                games.filter {
//                    it.doseMatchSearchQuery(text)
//                }
//            }
//        }
//        .onEach { _isSearching.update { false } }
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000),
//            _games.value
//        )
//
//    fun onSearchingTextChange(text: String) {
//        _searchText.value = text
//    }
//}

//data class Game(val title: String, val imageUri: String) {
//    fun doseMatchSearchQuery(query: String): Boolean {
//        val matchingCombinations = listOf(
//            title,
//            "${this.title.first()}"
//        )
//        return matchingCombinations.any {
//            it.contains(query, ignoreCase = true)
//        }
//    }
//}

//
//private val allGames = mutableListOf(
//    Game(
//        title = "Ganesh Nepal",
//        imageUri = "https://www.freetogame.com/g/427/thumbnail.jpg"
//    ),
//    Game(
//        "Hair Gautam",
//        "https://www.freetogame.com/g/475/thumbnail.jpg"
//    ),
//    Game(
//        "Sitaram Tamang",
//        "https://www.freetogame.com/g/523/thumbnail.jpg"
//    ),
//    Game(
//        "Ganesh Rai",
//        "https://www.freetogame.com/g/380/thumbnail.jpg"
//    ),
//)

// https://www.youtube.com/watch?v=CfL6Dl2_dAE