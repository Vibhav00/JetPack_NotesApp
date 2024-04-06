package com.vk.notesapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.notesapp.feature_note.domain.model.Note
import com.vk.notesapp.feature_note.domain.use_case.NoteUseCases
import com.vk.notesapp.feature_note.domain.util.NoteOrder
import com.vk.notesapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


/** Notes View Model **/
@HiltViewModel
class NotesViewModel @Inject constructor(
    /** add note . delete note , get note , get notes **/
    private val noteUseCases: NoteUseCases
) : ViewModel() {


    /** NOTES STATE **/
    private val _state = mutableStateOf(NotesState())


    /** variable that changes when _state changes and update ui **/
    val state: State<NotesState> = _state


    /** Recently Deleted notes Instance **/
    private var recentlyDeletedNote: Note? = null


    /** Storing old coroutine job **/
    private var getNotesJob: Job? = null



    init {
        /** Initialising the notes list **/
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            /** Order Notes **/
            is NotesEvent.Order -> {
                /** Comparing the classes ::class not the instance **/
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }
            /** Delete Notes **/
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    /** updating recently deleted note **/
                    recentlyDeletedNote = event.note
                }
            }
            /** Restore Note After delete **/
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            /** Toggle Order section **/
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        /** Destroying the last job **/
        getNotesJob?.cancel()
        /** Starting new job **/
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}