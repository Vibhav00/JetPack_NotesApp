package com.vk.notesapp.feature_note.presentation.notes



import com.vk.notesapp.feature_note.domain.model.Note
import com.vk.notesapp.feature_note.domain.util.NoteOrder



/** Different Note Events **/
sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
