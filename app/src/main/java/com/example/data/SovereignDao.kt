package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SovereignDao {
    @Insert
    suspend fun insertConflictGene(gene: ConflictGene)

    @Insert
    suspend fun insertLineageEvent(event: LineageEvent)

    @Query("SELECT * FROM conflict_genes ORDER BY riskScore DESC")
    fun getAllConflictGenes(): Flow<List<ConflictGene>>

    @Query("SELECT * FROM lineage_events ORDER BY timestamp DESC LIMIT 100")
    fun getRecentLineageEvents(): Flow<List<LineageEvent>>

    @Query("SELECT * FROM conflict_genes WHERE riskScore > :threshold")
    fun getHighRiskGenes(threshold: Double = 0.7): Flow<List<ConflictGene>>

    @Query("UPDATE conflict_genes SET riskScore = :risk, conflictCount = conflictCount + 1 WHERE filePath = :filePath")
    suspend fun updateRiskScore(filePath: String, risk: Double)
}
