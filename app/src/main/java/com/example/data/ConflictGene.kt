package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "conflict_genes")
data class ConflictGene(
    @PrimaryKey val geneId: String = UUID.randomUUID().toString(),
    val filePath: String,
    val riskScore: Float,
    val conflictCount: Int,
    val lastSeen: String,
    val pattern: String, // Stored as JSON string
    val coherenceImpact: Float
)
