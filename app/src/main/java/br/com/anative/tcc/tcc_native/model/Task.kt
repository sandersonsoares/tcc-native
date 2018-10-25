package br.com.anative.tcc.tcc_native.model

import java.text.SimpleDateFormat
import java.util.*

data class Task(
    var _id: String? = null,
    var title: String? = null,
    var description: String? = null,
    var is_finish: Boolean = false,
    var created_at: Date = Date(),
    var updated_at: Date = Date()
) {
    fun createdFormat(): String {
        return SimpleDateFormat("dd/MM/yyy HH:mm").format(created_at)
    }

    fun updatedFormat(): String {
        return SimpleDateFormat("dd/MM/yyy HH:mm").format(updated_at)
    }
}
