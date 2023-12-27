package com.ahmrh.serene.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmrh.serene.data.source.local.room.dao.SelfCareDao
import com.ahmrh.serene.data.source.local.room.entity.SelfCare

@Database(
    entities = [SelfCare::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun selfCareDao(): SelfCareDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "serene_database")
                    .build()
                    .also { Instance = it }
            }
        }

    }

}