package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        lateinit var binding: ActivityMainBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //views
        //val fabButton: FloatingActionButton = findViewById(R.id.fabButton)

        //bind click Handler
        binding.fabButton.setOnClickListener {
            clickHandlerFunction(it)
        }

        //bind object instance to layout
        val movie = Movie();
        binding.txtTitle.text = movie.title
        binding.txtGenreLbl.text = movie.genres
        binding.ratingBar.rating = movie.rating
        binding.txtCreatorsContent.text = movie.creators
        binding.txtActorsContent.text = movie.actors
        binding.txtDescriptionContent.setText(movie.description)

    }

    private fun clickHandlerFunction(viewThatIsClicked: View) {
        Toast.makeText(this, "button clicked",
                Toast.LENGTH_SHORT).show()
    }
}