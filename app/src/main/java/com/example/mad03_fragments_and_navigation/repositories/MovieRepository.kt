package com.example.mad03_fragments_and_navigation.repositories

import com.example.mad03_fragments_and_navigation.database.MovieDao
import com.example.mad03_fragments_and_navigation.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDao: MovieDao) {
    // TODO implement me
    suspend fun clearTable() =
        withContext(Dispatchers.IO){
            movieDao.clearTable()
        }

    suspend fun create(movie: Movie) =
        withContext(Dispatchers.IO){
            movieDao.create(movie)
        }

    suspend fun update(movie: Movie) =
        withContext(Dispatchers.IO){
            movieDao.update(movie)
        }

    suspend fun delete(movieId: Long) =
        withContext(Dispatchers.IO){
            movieDao.delete(movieId)
        }

    suspend fun getAll() =
        withContext(Dispatchers.IO){
            movieDao.getAll()
        }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }
}