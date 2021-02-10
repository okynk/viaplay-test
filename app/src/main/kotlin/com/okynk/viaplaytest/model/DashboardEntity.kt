package com.okynk.viaplaytest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Entity(tableName = "dashboard")
data class DashboardEntity(
    @PrimaryKey val primaryKey: String = "dashboard",
    val sections: List<LinkEntity>
)

@KoinApiExtension
class ListLinkEntityConverter : KoinComponent {

    private val gson: Gson by inject()

    @TypeConverter
    fun jsonToList(value: String): List<LinkEntity> =
        gson.fromJson(value, Array<LinkEntity>::class.java).toList()

    @TypeConverter
    fun listToJson(value: List<LinkEntity?>) = gson.toJson(value.filterNotNull())
}
