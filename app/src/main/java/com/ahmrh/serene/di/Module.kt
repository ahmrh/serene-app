package com.ahmrh.serene.di

import android.content.Context
import com.ahmrh.serene.data.repository.GamificationRepository
import com.ahmrh.serene.data.repository.PersonalizationRepository
import com.ahmrh.serene.data.repository.SelfCareRepository
import com.ahmrh.serene.data.repository.UserRepository
import com.ahmrh.serene.data.source.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {


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

}