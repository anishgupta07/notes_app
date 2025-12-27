package com.example.jetnotes.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnotes.repository.notesRepository
import com.example.jetnotes.room.NotesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: notesRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val notes = searchQuery
        .flatMapLatest { query ->
            if (query.isBlank())
                repository.getNotes()
            else
                repository.searchNote(query)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateSearch(query: String) {
        _searchQuery.value = query
    }

    fun addNotes(note: NotesEntity) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun deleteNotes(note: NotesEntity) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun updateNotes(note: NotesEntity) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun getNoteById(id: Int): Flow<NotesEntity?> {
        return repository.getNoteById(id)
    }

}
