package com.example.jetnotes.module

import android.app.Application
import androidx.room.Room
import com.example.jetnotes.room.NotesDao
import com.example.jetnotes.room.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NotesDatabase =
        Room.databaseBuilder(app, NotesDatabase::class.java, "note_db").build()

    @Provides
    fun provideDao(db: NotesDatabase): NotesDao = db.noteDao()
}
