package com.bookhub.bookhub.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.bookhub.bookhub.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

enum class PreferencesKeys(val value : String){
    TOKEN("token")
}

class LocalStorageUtil(private val applicationContext : Context) {

    fun getToken() : Flow<String?> {
        val key = stringPreferencesKey(PreferencesKeys.TOKEN.value)
        return applicationContext.dataStore.data.map {
            it[key]
        }
    }

    suspend fun setToken(token : String){
        val key = stringPreferencesKey(PreferencesKeys.TOKEN.value)
        applicationContext.dataStore.edit {
            it[key] = token
        }
    }
}