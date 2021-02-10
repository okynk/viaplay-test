package com.okynk.viaplaytest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface SectionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: SectionEntity): Completable

    @Query("SELECT * FROM sections WHERE id=:id LIMIT 1")
    fun getSection(id: String): Maybe<SectionEntity>
}