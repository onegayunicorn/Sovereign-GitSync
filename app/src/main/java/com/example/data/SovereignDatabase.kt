package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ConflictGene::class, LineageEvent::class], version = 1, exportSchema = false)
abstract class SovereignDatabase : RoomDatabase() {
    abstract fun sovereignDao(): SovereignDao
}
