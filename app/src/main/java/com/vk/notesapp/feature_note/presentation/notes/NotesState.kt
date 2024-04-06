package com.vk.notesapp.feature_note.presentation.notes

import com.vk.notesapp.feature_note.domain.model.Note
import com.vk.notesapp.feature_note.domain.util.NoteOrder
import com.vk.notesapp.feature_note.domain.util.OrderType


/** Notes state to monitor screen , (list of notes , notes order , order section visibility ) **/
data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
