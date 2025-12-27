package com.example.jetnotes.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NotesEntity::class],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao() : NotesDao
}