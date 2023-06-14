package com.chollan.kanapa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chollan.kanapa.model.AuthUser
import kotlinx.coroutines.launch

class AuthViewModel(private val pref: AuthPreferences) : ViewModel() {
    val authUser = pref.authUserFlow

    fun saveAuthUser(authUser: AuthUser) {
        viewModelScope.launch {
            pref.saveAuthUser(authUser)
        }
    }
}