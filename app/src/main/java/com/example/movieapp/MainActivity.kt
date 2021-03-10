package com.example.movieapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.TextView.BufferType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.movieapp.databinding.ActivityMainBinding

@BindingAdapter("android:text")
fun setText(view: TextView, list: MutableList<String>) {
    println("test")
    view.text = list.joinToString(", ")
}

class MainActivity : AppCompatActivity() {
    var movie = Movie();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        //bind click Handler
        binding.fabButton.setOnClickListener {
            clickHandlerFunction(it)
        }

        //bind object instance to layout
        binding.movie = movie;

    }

    private fun clickHandlerFunction(viewThatIsClicked: View) {
        Toast.makeText(this, "button clicked",
                Toast.LENGTH_SHORT).show()
    }
}