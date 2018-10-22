package br.com.anative.tcc.tcc_native.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ProgressBar
import android.widget.TextView
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.response.TasksResponse
import br.com.anative.tcc.tcc_native.api.services.TaskService
import br.com.anative.tcc.tcc_native.model.Task

class TasksAdapter(private val activity: Activity) : BaseAdapter() {

    var list: List<Task>? = null
    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(activity)
        dataUpdate()
    }

    fun dataUpdate() {
        var progressbar = activity.findViewById<ProgressBar>(R.id.progressbar)
        progressbar.visibility = ProgressBar.VISIBLE

        TaskService().list(object : ICallbackResponse<TasksResponse> {
            override fun success(instance: TasksResponse) {
                list = instance.tasks
                // Ajusta alterações
                this@TasksAdapter.notifyDataSetChanged()
                // Seta invisible no progress bar
                progressbar.visibility = ProgressBar.INVISIBLE
            }
        }, this.activity)
    }

    override fun getCount(): Int {
        if (list == null) {
            return 0
        }
        return list!!.size
    }

    override fun getItem(i: Int): Task? {
        if (list == null) {
            return null
        }
        return list!![i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.task_row, parent, false)
        } else {
            view = convertView
        }

//        var label = view?.findViewById<TextView>(R.id.titulo)
//        label?.text = "ID: " + getItem(i)?.id + " - " + getItem(i)?.nome
        return view
    }
}