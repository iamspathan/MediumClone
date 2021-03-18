package dev.iamspathan.realworldio.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.iamspathan.realworldapi.models.entities.Article
import dev.iamspathan.realworldio.data.ArticlesRepo
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val TAG = "FeedViewModel"

    private val _feed  = MutableLiveData<List<Article>>()

    val feed : LiveData<List<Article>> = _feed

    fun fetchGlobalFeed()  = viewModelScope.launch {
        ArticlesRepo.getGlobalFeed().let {
            _feed.postValue(it)
        }

    }


    fun fetchMyFeed() = viewModelScope.launch {
        ArticlesRepo.getMyFeed().let {
            _feed.postValue(it)
        }
    }



}