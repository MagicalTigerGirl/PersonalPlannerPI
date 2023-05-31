package com.example.personalplanner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.listafacturas.viewmodel.ComidaViewModel
import com.example.personalplanner.R
import com.example.personalplanner.databinding.ActivityMainBinding
import com.example.personalplanner.viewmodel.GastosViewModel
import io.ktor.util.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var comidaViewModel: ComidaViewModel
    private lateinit var gastosViewModel: GastosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val topLevelDestination: MutableSet<Int> = HashSet()
        topLevelDestination.add(R.id.FirstFragment)
        topLevelDestination.add(R.id.FiltrosComidaFragment)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestination).build()
        setupActionBarWithNavController(navController, appBarConfiguration)

        comidaViewModel  = getViewModel()
        gastosViewModel = getViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}