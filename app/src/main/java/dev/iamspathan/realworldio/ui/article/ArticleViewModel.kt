package dev.iamspathan.realworldio.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.iamspathan.realworldapi.ConduitClient
import dev.iamspathan.realworldapi.models.entities.Article
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel(){


    private val _article = MutableLiveData<Article>()

    val article : LiveData<Article> = _article


    val authAPI = ConduitClient.publicApi

    fun fetchArticleBySlug(slug:String) = viewModelScope.launch {
        authAPI.getArticlesBySlug(slug).body()?.article.let {
            _article.postValue(it)
        }
    }


}