package com.example.mad03_fragments_and_navigation

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.mad03_fragments_and_navigation.database.AppDatabase
import com.example.mad03_fragments_and_navigation.database.MovieDao
import com.example.mad03_fragments_and_navigation.databinding.FragmentMovieDetailBinding
import com.example.mad03_fragments_and_navigation.models.MovieStore
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieDetailFragment() : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)

        val args = MovieDetailFragmentArgs.fromBundle(requireArguments())   // get navigation arguments
        val movieEntry = MovieStore().findMovieByUUID(args.movieId)
        when(movieEntry){
            null -> {
                Toast.makeText(requireContext(), "Could not load movie data", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            else -> binding.movie = movieEntry
        }

        binding.addToFavorites.setOnClickListener {
            Toast.makeText(requireContext(), "Movie saved to favorites.", Toast.LENGTH_SHORT).show()
            val ctx = getActivity()?.applicationContext
            if (ctx !== null) {
                val movieDB = AppDatabase.getInstance(ctx).movieDao
                val repository: MovieRepository = MovieRepository(movieDB)


                var viewModelJob = Job()
                val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

                viewModelScope.launch {
                    if (movieEntry?.title !== null) {
                        // var movie = Movie(movieEntry.title, "")
                        repository.create(movieEntry)
                        val abc = repository.getAll()

                    }
                }
            }

        }

        return binding.root
    }
}