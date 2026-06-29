package com.example.data

import kotlinx.coroutines.flow.Flow

class SovereignRepository(private val dao: SovereignDao) {
    fun getAllConflictGenes(): Flow<List<ConflictGene>> = dao.getAllConflictGenes()
    fun getRecentLineageEvents(): Flow<List<LineageEvent>> = dao.getRecentLineageEvents()
    fun getHighRiskGenes(threshold: Double = 0.7): Flow<List<ConflictGene>> = dao.getHighRiskGenes(threshold)

    suspend fun recordConflictGene(gene: ConflictGene) = dao.insertConflictGene(gene)
    suspend fun recordLineageEvent(event: LineageEvent) = dao.insertLineageEvent(event)
    suspend fun updateRiskScore(filePath: String, risk: Double) = dao.updateRiskScore(filePath, risk)
}
