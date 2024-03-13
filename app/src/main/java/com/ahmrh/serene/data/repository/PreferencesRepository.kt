package com.ahmrh.serene.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ahmrh.serene.common.Category
import com.ahmrh.serene.common.CategoryUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        const val TAG = "PreferencesRepository"

        val FIRST_TIME_KEY = booleanPreferencesKey("first_time_key")
        val NOTIFICATION_KEY = booleanPreferencesKey("notification_key")
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_key")

        val SELF_CARE_STARTED_KEY =
            booleanPreferencesKey("self_care_started_key")
        val STARTED_ACTIVITY_ID_KEY =
            stringPreferencesKey("started_activity_id_key")

        val PERSONALIZATION_RESULT_KEY =
            stringPreferencesKey("personalization_result_key")
    }

    // FIRST TIME
    suspend fun changeFirstTimeValue(boolean: Boolean) {
        dataStore.edit { preferences ->
            preferences[FIRST_TIME_KEY] = boolean
        }
    }

    fun getFirstTimeValue(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[FIRST_TIME_KEY] ?: false
        }


    // NOTIFICATION
    suspend fun changeNotificationValue(boolean: Boolean) {
        dataStore.edit { preferences ->
            preferences[NOTIFICATION_KEY] = boolean
        }
    }

    fun getNotificationValue(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[NOTIFICATION_KEY] ?: false
        }

    // DARK MODE
    suspend fun changeDarkModeValue(boolean: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = boolean
        }
    }

    fun getDarkModeValue(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }

    // SELF CARE STARTED
    suspend fun changeSelfCareStartedValue(boolean: Boolean) {
        dataStore.edit { preferences ->
            preferences[SELF_CARE_STARTED_KEY] = boolean

        }
    }

    fun getSelfCareStartedValue(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[SELF_CARE_STARTED_KEY] ?: false
        }

    // STARTED ACTIVITY ID
    suspend fun changeStartedActivityIdValue(activityId: String) {
        dataStore.edit { preferences ->
            preferences[STARTED_ACTIVITY_ID_KEY] = activityId

        }
    }

    suspend fun clearStartedActivityIdValue() {
        dataStore.edit { preferences ->
            preferences.remove(STARTED_ACTIVITY_ID_KEY)
        }
    }

    fun getStartedActivityIdValue(): Flow<String?> =
        dataStore.data.map { preferences ->
            preferences[STARTED_ACTIVITY_ID_KEY]
        }

    // PERSONALIZATION RESULT
    suspend fun changePersonalizationResultValue(category: Category) {
        val categoryString = category.stringValue
        Log.d(TAG, "Changing personalization result value $categoryString")
        dataStore.edit { preferences ->
            preferences[PERSONALIZATION_RESULT_KEY] = categoryString
        }
    }

    fun getPersonalizationResultValue(): Flow<Category?> =
        dataStore.data
            .map { preferences -> preferences[PERSONALIZATION_RESULT_KEY] }
            .filterNotNull()
            .map{ categoryString -> CategoryUtils.getCategory(categoryString)}


}