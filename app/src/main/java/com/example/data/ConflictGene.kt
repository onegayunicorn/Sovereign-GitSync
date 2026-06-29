package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "conflict_genes")
data class ConflictGene(
    @PrimaryKey
    val geneId: String = UUID.randomUUID().toString(),
    val filePath: String,
    val riskScore: Double = 0.5,
    val conflictCount: Int = 0,
    val lastSeen: String = "",
    val pattern: String = "",
    val coherenceImpact: Double = 0.0
)
