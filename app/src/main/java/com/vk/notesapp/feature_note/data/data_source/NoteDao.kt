package com.vk.notesapp.feature_note.data.data_source

import androidx.room.*
import com.vk.notesapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


/** Notes Data Access Object  **/
@Dao
interface NoteDao {

    /** get all notes  **/
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    /** get note by id **/
    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    /** insert notes & update **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)


    /** delete a unique note  **/
    @Delete
    suspend fun deleteNote(note: Note)
}