package com.example.mad03_fragments_and_navigation.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mad03_fragments_and_navigation.models.Movie

@Dao
interface MovieDao {
    // TODO implement me
    /*
    o	create(movie: Movie): Long		-> creates a new favorite movie entry in DB
    o	update(movie: Movie)			-> updates a favorite movie with a new note
    o	delete(movieId: Long)			-> deletes a favorite movie
    o	clearTable()				-> deletes all favorite movies in table
    o	getAll(): LiveData<List<Movie>>	-> get all entries of favorite movies table

    -	Use proper annotations! Eg @Insert, @Update, @Query
*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(movie: Movie): Long
    @Update
    fun update(movie: Movie)
    @Query("DELETE FROM favorites_table WHERE id = :movieId")
    fun delete(movieId: Long)
    @Query("DELETE FROM favorites_table")
    fun clearTable()
    @Query("SELECT * FROM favorites_table")
    fun getAll(): List<Movie>

}