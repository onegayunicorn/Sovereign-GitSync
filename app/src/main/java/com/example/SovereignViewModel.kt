package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.SovereignRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.MutableStateFlow

class SovereignViewModel(private val repository: SovereignRepository) : ViewModel() {
    val conflictGenes = repository.getAllConflictGenes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
        
    val lineageEvents = repository.getRecentLineageEvents()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val coherenceScore = MutableStateFlow(0.9998)

    fun refresh() {
        // Mock refreshing
    }
}

class SovereignViewModelFactory(private val repository: SovereignRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SovereignViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SovereignViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
