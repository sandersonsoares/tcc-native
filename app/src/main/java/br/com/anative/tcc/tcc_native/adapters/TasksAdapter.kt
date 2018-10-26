package br.com.anative.tcc.tcc_native.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.api.response.DefaultResponse
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.response.TasksResponse
import br.com.anative.tcc.tcc_native.api.services.TaskService
import br.com.anative.tcc.tcc_native.fragments.TasksFragment
import br.com.anative.tcc.tcc_native.model.Task
import kotlinx.android.synthetic.main.task_row.view.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast


class TasksAdapter(public var tasks: MutableList<Task>, private val context: Context) :
    Adapter<TasksAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        var view = holder?.bindView(task)


        view.remove_btn.setOnClickListener {
            removerTask(task, context)
        }

        Log.i(">>>>>>>>>>>>>>>", task._id)

        view.item_title.setOnFocusChangeListener { view, b ->
            if (!view.isFocused) {
                if (task._id != null) {
                    atualizarTask(task, context)
                } else {
                    criarTask(task, context)
                }
            }
        }

        view.item_description.setOnFocusChangeListener { view, b ->
            if (!view.isFocused) {
                if (task._id != null) {
                    atualizarTask(task, context)
                } else {
                    criarTask(task, context)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun listarTasks(
        iCallbackResponse: ICallbackResponse<TasksResponse>? = null,
        context: Context
    ) {
        var progressbar = context.indeterminateProgressDialog("Carregando...")
        progressbar.setCancelable(false)
        progressbar.setCanceledOnTouchOutside(false)
        progressbar.show()

        TaskService().list(object : ICallbackResponse<TasksResponse> {
            override fun success(instance: TasksResponse) {
                progressbar.dismiss()
                if (instance.code.equals("000")) {
                    tasks = instance.tasks!!.toMutableList()
                    if (iCallbackResponse != null) {
                        iCallbackResponse?.success(instance = instance)
                    }
                } else {
                    context.toast(instance.message.toString())
                }
            }

            override fun error(instance: TasksResponse) {
                progressbar.dismiss()
                context.toast(instance.message.toString())
            }
        }, context)
    }

    private fun removerTask(task: Task, context: Context) {
        var progressbar = context.indeterminateProgressDialog("Carregando...")
        progressbar.setCancelable(false)
        progressbar.setCanceledOnTouchOutside(false)
        progressbar.show()

        TaskService().remove(task, object : ICallbackResponse<DefaultResponse> {
            override fun success(instance: DefaultResponse) {
                progressbar.dismiss()
                listarTasks(null, context)
                context.toast(instance.message.toString())
            }

            override fun error(instance: DefaultResponse) {
                progressbar.dismiss()
                context.toast(instance.message.toString())
            }
        }, context)

    }

    private fun criarTask(task: Task, context: Context) {
        TaskService().create(task, object : ICallbackResponse<DefaultResponse> {
            override fun success(instance: DefaultResponse) {
                var fragment = context as TasksFragment
                fragment.listarTasks()
                fragment.toast(instance.message.toString())
            }

            override fun error(instance: DefaultResponse) {
                context.toast(instance.message.toString())
            }
        }, context)
    }

    private fun atualizarTask(task: Task, context: Context) {
        TaskService().update(task, object : ICallbackResponse<DefaultResponse> {
            override fun success(instance: DefaultResponse) {
                var fragment = context as TasksFragment
                fragment.listarTasks()
                fragment.toast(instance.message.toString())

            }

            override fun error(instance: DefaultResponse) {
                context.toast(instance.message.toString())
            }
        }, context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(task: Task): View {
            itemView.item_title.setText(task.title)
            itemView.item_description.setText(task.description)
            itemView.created.text = "Última modificação: " + task.updatedFormat()
            return itemView
        }
    }


}