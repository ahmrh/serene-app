package com.ahmrh.serene.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareActivity
import com.ahmrh.serene.data.source.local.room.entity.selfcare.SelfCareHistory

@Dao
interface SelfCareDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selfCare: SelfCareActivity)

    @Delete
    suspend fun delete(selfCare: SelfCareActivity)

    @Query("DELETE FROM selfcare_activity WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(selfCareHistory: SelfCareHistory)

}