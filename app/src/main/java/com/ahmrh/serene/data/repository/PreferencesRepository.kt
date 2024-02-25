package com.ahmrh.serene.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val FIRST_TIME_KEY = booleanPreferencesKey("first_time_key")
        val NOTIFICATION_KEY = booleanPreferencesKey("notification_key")
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_key")
    }

    suspend fun changeFirstTimeValue(boolean: Boolean){
        dataStore.edit {preferences ->
            preferences[FIRST_TIME_KEY] = boolean
        }
    }

    fun getFirstTimeValue() : Flow<Boolean> =
        dataStore.data.map {preferences ->
            preferences[FIRST_TIME_KEY] ?: false
        }


    suspend fun changeNotificationValue(boolean: Boolean) {
        dataStore.edit {preferences ->
            preferences[NOTIFICATION_KEY] = boolean
        }
    }

    fun getNotificationValue() : Flow<Boolean> =
        dataStore.data.map {preferences ->
            preferences[NOTIFICATION_KEY] ?: false
        }



    suspend fun changeDarkModeValue(boolean: Boolean) {
        dataStore.edit {preferences ->
            preferences[DARK_MODE_KEY] = boolean
        }
    }

    fun getDarkModeValue() : Flow<Boolean> =
        dataStore.data.map {preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }


}