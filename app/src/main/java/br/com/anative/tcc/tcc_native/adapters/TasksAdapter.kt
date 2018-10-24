package br.com.anative.tcc.tcc_native.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.model.Task
import kotlinx.android.synthetic.main.task_row.view.*

class TasksAdapter(private val list: List<Task>, private val context: Context) : Adapter<TasksAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = list[position]
        holder?.bindView(task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(task: Task) {
            val title = itemView.note_item_title
            val description = itemView.note_item_description

            title.text = task.title
            description.text = task.description
        }
    }

}