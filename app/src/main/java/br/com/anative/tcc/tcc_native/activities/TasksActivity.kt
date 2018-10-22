package br.com.anative.tcc.tcc_native.activities

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.adapters.TasksAdapter
import kotlinx.android.synthetic.main.tasks_activity.*

class TasksActivity : DefaultActivity() {
    var tasksAdapter: TasksAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_activity)


        // Inicializa o adaptar
        tasksAdapter = TasksAdapter(this)
        // list é pego automático pelo ID na View
        // Seta o adapter na view
        list.adapter = tasksAdapter

        // Definir evento
        list.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            toast("Categoria: ${tasksAdapter!!.getItem(i)!!.description}")
        }

        // Atualiza a listagem
        swipeToRefresh.setOnRefreshListener {
            swipeToRefresh.isRefreshing = false
            tasksAdapter!!.dataUpdate()
        }

        // Ativando o FloatingActionButton
//        buttonadd.setOnClickListener() {
//            var intent = Intent(this, AddCategoriaActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onRestart() {
        super.onRestart()
        // Atualia os dados do adapter na view
        tasksAdapter!!.dataUpdate()
    }

}