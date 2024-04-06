package com.vk.notesapp.feature_note.domain.util


/** different order types **/
sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
