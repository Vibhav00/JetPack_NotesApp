package com.vk.notesapp.feature_note.domain.use_case

import com.vk.notesapp.feature_note.domain.model.Note
import com.vk.notesapp.feature_note.domain.repository.NoteRepository
import com.vk.notesapp.feature_note.domain.util.NoteOrder
import com.vk.notesapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when(noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        /** if the filter is for sorting in Ascending and by title  **/
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        /** if the filter is for sorting in Ascending and by timestamp  **/
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        /** if the filter is for sorting in Ascending and by color  **/
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        /** if the filter is for sorting in Descending  and by title  **/
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        /** if the filter is for sorting in Descending and by timestamp  **/
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        /** if the filter is for sorting in Descending and by color  **/
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}