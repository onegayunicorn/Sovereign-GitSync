package com.example.api

import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

@Serializable
data class ChatRequest(val message: String, val context: String? = null, val sessionId: String? = null)

@Serializable
data class ChatResponse(val response: String, val sessionId: String, val timestamp: String, val confidence: Double = 0.95)

@Serializable
data class CouncilRequest(val query: String, val context: Map<String, String> = emptyMap())

@Serializable
data class CouncilResponse(val consensus: Double, val decision: String, val votes: Map<String, Boolean>, val reasoning: String)

interface GeminiApiService {
    @POST("/api/chat")
    suspend fun sendMessage(@Body request: ChatRequest): ChatResponse

    @POST("/api/council/chat")
    suspend fun consultCouncil(@Body request: CouncilRequest): CouncilResponse

    @GET("/git/conflicts")
    suspend fun getConflicts(): List<ConflictData>
}

@Serializable
data class ConflictData(val id: String, val file: String, val type: String, val severity: Double, val coherence_impact: Double)
