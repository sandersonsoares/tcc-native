package br.com.anative.tcc.tcc_native.fragments


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.activities.HomeActivity
import br.com.anative.tcc.tcc_native.adapters.TasksAdapter
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.response.TasksResponse
import br.com.anative.tcc.tcc_native.api.services.TaskService
import br.com.anative.tcc.tcc_native.model.Task
import br.com.anative.tcc.tcc_native.util.SharedPreferencesUtil


class TasksFragment : Fragment() {

    var tasks: List<Task>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        var fragment: View = inflater.inflate(R.layout.tasks_fragment, container, false)
        var homeActivity = activity as HomeActivity
        var sharedPreferencesUtil = SharedPreferencesUtil(homeActivity)
        var progressBar = fragment.findViewById(R.id.progressbar) as ProgressBar

        progressBar.visibility = ProgressBar.VISIBLE

        TaskService().list(object : ICallbackResponse<TasksResponse> {
            override fun success(instance: TasksResponse) {
                progressBar.visibility = ProgressBar.INVISIBLE
                if (instance.code.equals("000")) {
                    tasks = instance.tasks

                    System.out.println(tasks)

                    val recyclerView = fragment.findViewById(R.id.list) as RecyclerView
                    recyclerView.adapter = TasksAdapter(instance.tasks!!, homeActivity)
                    val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView.layoutManager = layoutManager

                } else {
                    Toast.makeText(homeActivity, instance.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }, homeActivity)

        return fragment
    }

    private fun tasks(): List<Task> {
        return listOf(
            Task(
                title = "Leitura",
                description = "Livro de Kotlin com Android"
            ),
            Task(
                title = "Pesquisa",
                description = "Como posso melhorar o código dos meus projetos"
            ),
            Task(
                title = "Estudo",
                description = "Como sincronizar minha App com um Web Service"
            )
        )
    }


    fun newInstance(): TasksFragment {
        return TasksFragment()
    }


}
