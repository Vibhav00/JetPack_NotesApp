package com.vk.notesapp.feature_note.domain.use_case


import com.vk.notesapp.feature_note.domain.model.InvalidNoteException
import com.vk.notesapp.feature_note.domain.model.Note
import com.vk.notesapp.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        /** Checking for the notes title **/
        if(note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        /** Checking the content of Note **/
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        /** Inserting the valid note **/
        repository.insertNote(note)
    }
}