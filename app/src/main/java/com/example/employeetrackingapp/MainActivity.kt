package com.example.employeetrackingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.employeetrackingapp.base.BaseActivity
import com.example.employeetrackingapp.base.BaseViewModel
import com.example.employeetrackingapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    var navController: NavController? = null

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    override val viewModel: BaseViewModel by viewModels()

    override fun onActivityCreated() {
        navController = Navigation.findNavController(this, R.id.navHostFragment)
    }

    override fun observe() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId){
                R.id.nav_tab_emp_tracking -> {
                    title = "Görev Takibi"
                    navController?.navigate(R.id.employeeTrackingFragment)
                    true
                }

                R.id.nav_tab_employee -> {
                    title = "Personel Listesi"
                    navController?.navigate(R.id.employeeListFragment)
                    true
                }
                R.id.nav_tab_process -> {
                    title = "İşlem Listesi"
                    navController?.navigate(R.id.processListFragment)
                    true
                }
                else -> {
                    navController!!.navigate(R.id.employeeTrackingFragment)
                    true
                }
            }
        }
    }

    override fun onBackPressed() {
        when(navController?.currentDestination!!.id) {
            R.id.employeeListFragment -> {
                navController!!.navigate(R.id.employeeTrackingFragment)
                binding.bottomNavigation.checkItem(R.id.nav_tab_emp_tracking)
            }
            R.id.processListFragment -> {
                navController!!.navigate(R.id.employeeTrackingFragment)
                binding.bottomNavigation.checkItem(R.id.nav_tab_emp_tracking)
            }
            R.id.employeeTrackingFragment -> {
                finish()
            }
        }
    }
    fun BottomNavigationView.checkItem(actionId: Int) {
        menu.findItem(actionId)?.isChecked = true
    }
}

