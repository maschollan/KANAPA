package com.chollan.kanapa.viewmodel

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.chollan.kanapa.model.AuthUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreferences(private val context: Context) {

    val authUserFlow: Flow<AuthUser> = context.dataStore.data.map { preferences ->
        val name = preferences[NAME] ?: ""
        val email = preferences[EMAIL] ?: ""
        val token = preferences[TOKEN] ?: ""
        AuthUser(name, email, token)
    }

    suspend fun saveAuthUser(authUser: AuthUser) {
        Log.d("LALA", "Mulai Edit CUK")
        context.dataStore.edit { preferences ->
            preferences[NAME] = authUser.name
            preferences[EMAIL] = authUser.email
            preferences[TOKEN] = authUser.token
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_pref")

        val NAME = stringPreferencesKey("name_string")
        val EMAIL = stringPreferencesKey("email_string")
        val TOKEN = stringPreferencesKey("token_string")
    }
}