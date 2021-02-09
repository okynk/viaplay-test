package com.okynk.viaplaytest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.ListLinkEntityConverter
import com.okynk.viaplaytest.model.SectionEntity

@Database(
    entities = [
        DashboardEntity::class,
        SectionEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ListLinkEntityConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dashboardDao(): DashboardDao

    abstract fun sectionsDao(): SectionsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigrationOnDowngrade().build()

                INSTANCE = instance

                instance
            }
        }
    }
}