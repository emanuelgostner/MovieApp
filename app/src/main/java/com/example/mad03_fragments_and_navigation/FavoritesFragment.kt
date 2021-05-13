package com.example.mad03_fragments_and_navigation

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mad03_fragments_and_navigation.adapters.FavoritesListAdapter
import com.example.mad03_fragments_and_navigation.database.AppDatabase
import com.example.mad03_fragments_and_navigation.databinding.FragmentFavoritesBinding
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.repositories.MovieRepository
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModel
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModelFactory


class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    lateinit var favoritesViewModel: MovieFavoritesViewModel
    private var m_Text: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        val ctx = getActivity()?.applicationContext
        if (ctx !== null) {
            val movieDB = AppDatabase.getInstance(ctx).movieDao
            val repository: MovieRepository = MovieRepository(movieDB)
            val viewModelFactory = MovieFavoritesViewModelFactory(repository)
            favoritesViewModel =
                ViewModelProvider(
                    this, viewModelFactory).get(MovieFavoritesViewModel::class.java)
        }

        val adapter = FavoritesListAdapter(
            dataSet = listOf(),     // start with empty list
            onDeleteClicked = {movieId -> onDeleteMovieClicked(movieId)},   // pass functions to adapter
            onEditClicked = {movie -> onEditMovieClicked(movie)},           // pass functions to adapter
        )


        favoritesViewModel.getAll()
        val movieList = favoritesViewModel.movies.value
        if(movieList !== null)
            adapter.updateDataSet(movieList)
        with(binding){
            recyclerView.adapter = adapter
        }
        favoritesViewModel.movies.observe(viewLifecycleOwner, Observer { newMovieList ->
            print(newMovieList)
            adapter.updateDataSet(newMovieList)
        })
        binding.clearBtn.setOnClickListener { view : View ->
            favoritesViewModel.clearTable()
        }
        return binding.root
    }

    // This is called when recyclerview item edit button is clicked
    private fun onEditMovieClicked(movieObj: Movie){
        // TODO implement me
        var ctx = this.context
        if(ctx !== null) {
            var applicationContext = getActivity()?.applicationContext
            val builder = AlertDialog.Builder(ctx)
            val inflater = layoutInflater
            builder.setTitle("With EditText")
            val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.editText)
            builder.setView(dialogLayout)
            builder.setPositiveButton("OK") {
                dialogInterface, i ->
                movieObj.note = editText.text.toString()
                favoritesViewModel.update(movieObj)
                //dialogInterface, i -> Toast.makeText(applicationContext, "EditText is " + editText.text.toString(), Toast.LENGTH_SHORT).show()
            }
            builder.show()

        }
    }

    // This is called when recyclerview item remove button is clicked
    private fun onDeleteMovieClicked(movieId: Long){
        // TODO implement me
        favoritesViewModel.delete(movieId)
    }
}