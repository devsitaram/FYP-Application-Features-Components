package com.edu.appfeature.features.searchpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _games = MutableStateFlow(allGames) // set list of data where get from api
    val games = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_games) { text, games ->
            if (text.isBlank()) {
                games
            } else {
                delay(2000L)
                games.filter {
                    it.doseMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _games.value
        )

    fun onSearchingTextChange(text: String) {
        _searchText.value = text
    }
}

data class Person(val name: String, val imageUri: String) {
    fun doseMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            name,
            "${this.name.first()}"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val allGames = listOf(
    Person(
        "Santosh Nepal",
        "https://www.freetogame.com/g/427/thumbnail.jpg"
    ),
    Person(
        "Hair Gautam",
        "https://www.freetogame.com/g/475/thumbnail.jpg"
    ),
    Person(
        "Sitaram Tamang",
        "https://www.freetogame.com/g/523/thumbnail.jpg"
    ),
    Person(
        "Ganesh Rai",
        "https://www.freetogame.com/g/380/thumbnail.jpg"
    ),
)

// https://www.youtube.com/watch?v=CfL6Dl2_dAE