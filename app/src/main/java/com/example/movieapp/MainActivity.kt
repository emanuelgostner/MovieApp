package com.example.movieapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.TextView.BufferType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.databinding.ActivityMainBinding
@BindingAdapter("android:text")
fun setText(view: TextView, list: MutableList<String>) {
    view.text = list.joinToString(", ")
}
class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "Lifecycle hook: onCreate")
        val binding : ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = this.findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this,navController)
        drawerLayout = binding.drawerLayout
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "Lifecycle hook: onStart")
    }
    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "Lifecycle hook: onPause")
    }
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "Lifecycle hook: onResume")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "Lifecycle hook: onDestroy")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "Lifecycle hook: onRestart")
    }
    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "Lifecycle hook: onStop")
    }
}