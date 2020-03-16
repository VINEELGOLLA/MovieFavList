package com.example.moviefavlist.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviefavlist.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class Second : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)



        val navview: BottomNavigationView =  findViewById(R.id.nav_view)


        val  navController = findNavController(R.id.my_nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.userPage3,
                R.id.favlist4,
                R.id.userDetail
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navview.setupWithNavController(navController)


    }


}
