package com.okynk.viaplaytest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface SectionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: SectionEntity)

    @Query("SELECT * FROM sections WHERE id=:id LIMIT 1")
    fun getSection(id: String): Single<SectionEntity>

    @Query("DELETE FROM sections")
    fun deleteAll()
}