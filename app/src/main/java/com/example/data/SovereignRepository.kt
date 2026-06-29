package com.example.data

import kotlinx.coroutines.flow.Flow

class SovereignRepository(private val dao: SovereignDao) {
    val allConflictGenes: Flow<List<ConflictGene>> = dao.getAllConflictGenes()
    val allLineageEvents: Flow<List<LineageEvent>> = dao.getAllLineageEvents()

    suspend fun insertConflictGene(gene: ConflictGene) = dao.insertConflictGene(gene)
    suspend fun insertLineageEvent(event: LineageEvent) = dao.insertLineageEvent(event)
}
