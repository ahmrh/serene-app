package com.ahmrh.serene.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.PersonalizationRepository
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.data.repository.SelfCareRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//private val Context.dataStore by preferencesDataStore("preferences")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideGamificationRepository(
        @ApplicationContext context: Context
    ): GamificationRepository {
        return GamificationRepository()
    }

    @Provides
    @Singleton
    fun providePersonalizationRepository(
        @ApplicationContext context: Context
    ): PersonalizationRepository {
        return PersonalizationRepository(
            storage = Firebase.storage,
            firestore = Firebase.firestore
        )

    }

    @Provides
    @Singleton
    fun provideSelfCareRepository(
        @ApplicationContext context: Context
    ): SelfCareRepository {
        return SelfCareRepository(
            storage = Firebase.storage,
            firestore = Firebase.firestore
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        @ApplicationContext context: Context
    ): UserRepository {
        return UserRepository(
            auth = Firebase.auth
        )
    }

    @Provides
    @Singleton
    fun providePreferencesRepository (
        dataStore: DataStore<Preferences>
    ): PreferencesRepository {
        return PreferencesRepository(dataStore)
    }

    @Provides
    @Singleton
    fun providePreferenceDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler (
            produceNewData = { emptyPreferences() }
        ),
        produceFile = { context.preferencesDataStoreFile("preferences")}
    )



}