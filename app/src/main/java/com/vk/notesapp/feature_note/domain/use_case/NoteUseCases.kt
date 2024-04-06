package com.vk.notesapp.feature_note.domain.use_case


/** -:  Different Notes Use Cases :---**/
data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
