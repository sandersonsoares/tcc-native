package br.com.anative.tcc.tcc_native.fragments


import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.adapters.TasksAdapter
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.response.TasksResponse
import br.com.anative.tcc.tcc_native.api.services.TaskService
import br.com.anative.tcc.tcc_native.model.Task
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast


class TasksFragment : Fragment() {

    var tasks: MutableList<Task> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        var fragment = inflater.inflate(R.layout.tasks_fragment, container, false)

        var swipeRefreshLayout = fragment.findViewById(R.id.task_swipeToRefresh) as SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            onResume()
        }

        var buttonadd = fragment.findViewById(R.id.buttonadd) as FloatingActionButton
        buttonadd.setOnClickListener {
            adicionarTask()
        }

        return fragment
    }

    override fun onResume() {
        var progressbar = indeterminateProgressDialog("Carregando...")
        progressbar.setCancelable(false)
        progressbar.setCanceledOnTouchOutside(false)
        progressbar.show()

        TaskService().list(object : ICallbackResponse<TasksResponse> {
            override fun success(instance: TasksResponse) {
                progressbar.dismiss()
                if (instance.code.equals("000")) {
                    tasks = instance.tasks!!.toMutableList()
                    buildReciclerView().adapter = TasksAdapter(tasks, this@TasksFragment.activity)
                } else {
                    toast(instance.message.toString())
                }
                toast(instance.message.toString())
            }

            override fun error(instance: TasksResponse) {
                progressbar.dismiss()
                toast(instance.message.toString())
            }

        }, this.activity)

        super.onResume()
    }


    fun adicionarTask() {
        var task = Task()
        tasks.add(task)
        buildReciclerView().adapter = TasksAdapter(tasks, this@TasksFragment.activity)
    }

    fun removerTask() {

    }

    private fun buildReciclerView(): RecyclerView {
        val recyclerView = this.activity.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.adapter = TasksAdapter(arrayListOf(), this.activity)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        return recyclerView
    }


    fun newInstance(): TasksFragment {
        return TasksFragment()
    }


}
