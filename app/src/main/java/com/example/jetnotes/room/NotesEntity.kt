package com.example.jetnotes.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "notes-table")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val description : String,
    val timestamp: Long = System.currentTimeMillis()
)