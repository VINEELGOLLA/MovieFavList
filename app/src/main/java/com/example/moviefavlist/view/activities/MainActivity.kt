package com.example.moviefavlist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviefavlist.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* val navview: BottomNavigationView =  findViewById(R.id.nav_view)

        val  navController = findNavController(R.id.my_nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.userPage, R.id.favlist
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navview.setupWithNavController(navController)
*/
    }
}