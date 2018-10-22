package br.com.anative.tcc.tcc_native.fragments


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anative.tcc.tcc_native.R


class TasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.tasks_fragment, container, false)
    }

    fun newInstance(): TasksFragment {
        return TasksFragment()
    }


}
