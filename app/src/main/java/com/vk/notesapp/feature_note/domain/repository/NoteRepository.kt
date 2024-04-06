package com.vk.notesapp.feature_note.domain.repository


import com.vk.notesapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


/** blue print of Repository for use / testing **/
interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}