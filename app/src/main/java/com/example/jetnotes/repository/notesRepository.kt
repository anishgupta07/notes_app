package com.example.jetnotes.repository

import com.example.jetnotes.room.NotesDao
import com.example.jetnotes.room.NotesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class notesRepository @Inject constructor(
   private val dao: NotesDao
) {
    fun getNotes() : Flow<List<NotesEntity>> = dao.getAllNotes()

    suspend fun insertNote(note : NotesEntity){
        dao.addNotes(note)
    }

    suspend fun  updateNote(note : NotesEntity){
        dao.updateNotes(note)
    }

    suspend fun  deleteNote(note : NotesEntity){
        dao.deleteNotes(note)
    }

    fun searchNote(query : String) : Flow<List<NotesEntity>> = dao.searchNotes(query)

    fun getNoteById(id: Int): Flow<NotesEntity?> {
        return dao.getNoteById(id)
    }

}