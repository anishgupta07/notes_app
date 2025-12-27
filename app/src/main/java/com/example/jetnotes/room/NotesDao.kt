package com.example.jetnotes.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM `notes-table`")
    fun getAllNotes(): Flow<List<NotesEntity>>

    @Query("""
        SELECT * FROM `notes-table`
        WHERE title LIKE '%' || :query || '%'
        OR description LIKE '%' || :query || '%'
    """)
    fun searchNotes(query: String): Flow<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotes(notesEntity: NotesEntity)

    @Update
    suspend fun updateNotes(notesEntity: NotesEntity)

    @Delete
    suspend fun deleteNotes(notesEntity: NotesEntity)

    @Query("SELECT * FROM `notes-table` WHERE id = :id LIMIT 1")
    fun getNoteById(id: Int): Flow<NotesEntity?>
}
