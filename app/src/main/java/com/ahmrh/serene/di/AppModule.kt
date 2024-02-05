package com.ahmrh.serene.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.PersonalizationRepository
import com.ahmrh.serene.data.repository.PreferencesRepository
import com.ahmrh.serene.data.repository.SelfCareRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.data.source.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
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
        return GamificationRepository(
            AppDatabase.getDatabase(context).achievementDao(),
            AppDatabase.getDatabase(context).challengeDao()
        )
    }

    @Provides
    @Singleton
    fun providePersonalizationRepository(
        @ApplicationContext context: Context
    ): PersonalizationRepository {
        return PersonalizationRepository(
            AppDatabase.getDatabase(context).personalizationDao()
        )
    }

    @Provides
    @Singleton
    fun provideSelfCareRepository(
        @ApplicationContext context: Context
    ): SelfCareRepository {
        return SelfCareRepository(
            AppDatabase.getDatabase(context).selfCareDao(),
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        @ApplicationContext context: Context
    ): UserRepository {
        return UserRepository(
            AppDatabase.getDatabase(context).profileDao(),
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