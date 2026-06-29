package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SovereignDao {
    @Query("SELECT * FROM conflict_genes")
    fun getAllConflictGenes(): Flow<List<ConflictGene>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConflictGene(gene: ConflictGene)

    @Query("SELECT * FROM lineage_events ORDER BY timestamp DESC")
    fun getAllLineageEvents(): Flow<List<LineageEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLineageEvent(event: LineageEvent)
}
