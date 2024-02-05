package com.ahmrh.serene.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmrh.serene.data.source.local.room.converter.PersonalizationPointConverter
import com.ahmrh.serene.data.source.local.room.dao.AchievementDao
import com.ahmrh.serene.data.source.local.room.dao.ChallengeDao
import com.ahmrh.serene.data.source.local.room.dao.PersonalizationDao
import com.ahmrh.serene.data.source.local.room.dao.ProfileDao
import com.ahmrh.serene.data.source.local.room.dao.SelfCareDao
import com.ahmrh.serene.data.source.local.room.entity.gamification.achievement.Achievement
import com.ahmrh.serene.data.source.local.room.entity.gamification.achievement.AchievementHistory
import com.ahmrh.serene.data.source.local.room.entity.gamification.challenge.Challenge
import com.ahmrh.serene.data.source.local.room.entity.gamification.challenge.ChallengeHistory
import com.ahmrh.serene.data.source.local.room.entity.personalization.Question
import com.ahmrh.serene.data.source.local.room.entity.personalization.Result
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareActivity
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareCategory
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareHistory
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareRecommendation
import com.ahmrh.serene.data.source.local.room.entity.user.Profile

@Database(
    entities = [
        SelfCareActivity::class,
        SelfCareCategory::class,
        SelfCareHistory::class,
        SelfCareRecommendation::class,
        Profile::class,
        Question::class,
        Result::class,
        Challenge::class,
        ChallengeHistory::class,
        Achievement::class,
        AchievementHistory::class
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(PersonalizationPointConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun achievementDao(): AchievementDao
    abstract fun challengeDao(): ChallengeDao
    abstract fun personalizationDao(): PersonalizationDao
    abstract fun profileDao(): ProfileDao
    abstract fun selfCareDao(): SelfCareDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return Instance ?: synchronized(
                this
            ) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "serene_database"
                ).build().also { Instance = it }
            }
        }


    }

}