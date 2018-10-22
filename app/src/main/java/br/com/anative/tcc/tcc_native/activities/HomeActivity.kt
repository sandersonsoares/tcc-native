package br.com.anative.tcc.tcc_native.activities

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.fragments.ProfileFragment
import br.com.anative.tcc.tcc_native.fragments.TasksFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : DefaultActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                var tasksFragment: Fragment = TasksFragment().newInstance()
                openFragment(tasksFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_notifications -> {
                var profileFragment: Fragment = ProfileFragment().newInstance()
                openFragment(profileFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = this.fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        var tasksFragment: Fragment = TasksFragment().newInstance()
        openFragment(tasksFragment)
    }
}
