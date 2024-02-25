package com.ahmrh.serene.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preference")

@Singleton
class AppPreferences @Inject constructor(
    @ApplicationContext context: Context
) {

//    companion object {
//        const val TAG = "AppPreferences"
//        @Volatile
//        private var INSTANCE: AppPreferences? = null
//
//        fun getInstance(dataStore: DataStore<Preferences>): AppPreferences {
//            return INSTANCE ?: synchronized(this) {
//                val instance = AppPreferences(dataStore)
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//    private val preferenceDataStore: DataStore<Preferences> = context.dataStore


}