package com.ahmrh.serene.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmrh.serene.data.source.local.room.entity.SelfCare

@Dao
interface SelfCareDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(selfCare: SelfCare)

    @Delete
    suspend fun delete(selfCare: SelfCare)

    @Query("DELETE FROM selfcare WHERE id = :id")
    suspend fun deleteById(id: Int)

}