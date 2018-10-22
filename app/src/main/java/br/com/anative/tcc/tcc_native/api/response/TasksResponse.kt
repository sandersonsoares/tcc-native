package br.com.anative.tcc.tcc_native.api.response

import br.com.anative.tcc.tcc_native.model.Task

class TasksResponse(
    var tasks: List<Task>? = null
) : DefaultResponse()