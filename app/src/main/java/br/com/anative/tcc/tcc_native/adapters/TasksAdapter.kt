package br.com.anative.tcc.tcc_native.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.model.Task
import kotlinx.android.synthetic.main.task_row.view.*
import org.jetbrains.anko.indeterminateProgressDialog


class TasksAdapter(private val tasks: MutableList<Task>, private val context: Context) :
    Adapter<TasksAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        var view = holder?.bindView(task)

        view.remove_btn.setOnClickListener {
            removerTask(task._id.toString(), context)
        }

        view.item_title.setOnFocusChangeListener { view, b ->
            if (!view.isFocused) {
                Log.i("beforeTextChanged >>>> ", view.item_title.text.toString())
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


    private fun removerTask(id: String, context: Context) {
        var progressbar = context.indeterminateProgressDialog("Carregando...")
        progressbar.setCancelable(false)
        progressbar.setCanceledOnTouchOutside(false)
        progressbar.show()

    }

    private fun criarTask() {

    }

    private fun atualizarTask() {

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