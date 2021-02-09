package com.okynk.viaplaytest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.okynk.viaplaytest.model.DashboardEntity
import io.reactivex.rxjava3.core.Maybe

@Dao
interface DashboardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: DashboardEntity)

    @Query("SELECT * FROM dashboard LIMIT 1")
    fun getDashboard(): Maybe<DashboardEntity>

    @Query("DELETE FROM dashboard")
    fun deleteAll()
}