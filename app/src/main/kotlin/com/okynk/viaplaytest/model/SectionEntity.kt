package com.okynk.viaplaytest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sections")
data class SectionEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String
)
