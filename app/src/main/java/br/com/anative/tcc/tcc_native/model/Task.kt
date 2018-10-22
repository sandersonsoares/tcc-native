package br.com.anative.tcc.tcc_native.model

import java.util.*

data class Task(
    var _id: String? = null,
    var description: String? = null,
    var is_finish: Boolean = false,
    var created_at: Date,
    var updated_at: Date
)