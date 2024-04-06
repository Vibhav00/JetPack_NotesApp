package com.vk.notesapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vk.notesapp.ui.theme.*



/** data class for the Note Entity**/
@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        /** list of note colors  **/
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

/** Invalid Notes Exception  **/
class InvalidNoteException(message: String): Exception(message)