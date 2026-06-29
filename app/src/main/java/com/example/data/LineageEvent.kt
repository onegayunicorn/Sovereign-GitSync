package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "lineage_events")
data class LineageEvent(
    @PrimaryKey
    val eventId: String = UUID.randomUUID().toString(),
    val eventType: String,
    val timestamp: String,
    val data: String,
    val coherenceDelta: Double = 0.0,
    val severity: Int = 1
)
