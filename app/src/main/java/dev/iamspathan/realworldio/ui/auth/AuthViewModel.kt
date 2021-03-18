package dev.iamspathan.realworldio.ui.auth

import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.iamspathan.realworldapi.models.entities.User
import dev.iamspathan.realworldio.data.UserRepo
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private var _user = MutableLiveData<User?>()

    val user: LiveData<User?> = _user

    fun getCurrentUser(token: String) = viewModelScope.launch {
        UserRepo.getCurrentUser(token = token).let {
            _user.postValue(it)
        }
    }

    fun login(email: String, password: String) {

        viewModelScope.launch {
            UserRepo.login(email, password)?.let {
                _user.postValue(it)
            }
        }
    }

    fun signup(userName: String, email: String, password: String) {
        viewModelScope.launch {
            UserRepo.signup(userName, email, password).let {
                _user.postValue(it)
            }
        }
    }

    fun update(
        bio: String?,
        email: String?,
        image: String?,
        userName: String?,
        password: String?,
    ) {
        viewModelScope.launch {
            UserRepo.updateUser(bio, email, image, userName, password)?.let {
                _user.postValue(it)

            }
        }
    }

    fun logout() {
        _user.postValue(null)
    }
}