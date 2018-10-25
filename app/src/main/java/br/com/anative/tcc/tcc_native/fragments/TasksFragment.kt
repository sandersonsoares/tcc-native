package br.com.anative.tcc.tcc_native.fragments


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.adapters.TasksAdapter
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.response.TasksResponse
import br.com.anative.tcc.tcc_native.api.services.TaskService
import br.com.anative.tcc.tcc_native.model.Task
import org.jetbrains.anko.indeterminateProgressDialog


class TasksFragment : Fragment() {

    var tasks: List<Task> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.tasks_fragment, container, false)
    }

    override fun onResume() {
        val recyclerView = this.activity.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.adapter = TasksAdapter(tasks.toList(), this.activity)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        var progressbar = indeterminateProgressDialog("Carregando...")
        progressbar.setCancelable(false)
        progressbar.setCanceledOnTouchOutside(false)
        progressbar.show()

        TaskService().list(object : ICallbackResponse<TasksResponse> {
            override fun success(instance: TasksResponse) {
                progressbar.dismiss()
                if (instance.code.equals("000")) {
                    tasks = instance.tasks!!.toList()
                    recyclerView.adapter = TasksAdapter(tasks, this@TasksFragment.activity)
                } else {
                    Toast.makeText(this@TasksFragment.activity, instance.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        }, this.activity)

        super.onResume()
    }


    fun newInstance(): TasksFragment {
        return TasksFragment()
    }


}
