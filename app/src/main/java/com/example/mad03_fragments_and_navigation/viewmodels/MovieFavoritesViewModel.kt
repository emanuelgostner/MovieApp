package com.example.mad03_fragments_and_navigation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieFavoritesViewModel (
    private val repository: MovieRepository
): ViewModel() {
    // TODO implement me
    val movies = MutableLiveData<MutableList<Movie>>()
    //val moviess = repository.getAll()
    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun clearTable() {
        viewModelScope.launch{
            repository.clearTable()
            getAll()
        }
    }
    fun update(movie: Movie) {
        viewModelScope.launch{
            repository.update(movie)
            getAll()
        }

    }
    fun delete(movieId: Long) {
        viewModelScope.launch{
            repository.delete(movieId)
            getAll()
        }
    }
    fun create(movie: Movie) {
        viewModelScope.launch{
            repository.create(movie)
        }
    }
    fun getAll() {
        viewModelScope.launch{
            var moviesFromDB = repository.getAll()
            print(moviesFromDB)
            //var abc = moviesFromDB?.value?.get(0)
            movies.value = moviesFromDB.toMutableList()
        }
    }
}