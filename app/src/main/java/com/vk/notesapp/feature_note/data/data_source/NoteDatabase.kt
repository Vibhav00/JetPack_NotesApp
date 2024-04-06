package com.vk.notesapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vk.notesapp.feature_note.domain.model.Note


/** Notes Database  **/
@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        /** Name of the Database **/
        const val DATABASE_NAME = "notes_db"
    }
}