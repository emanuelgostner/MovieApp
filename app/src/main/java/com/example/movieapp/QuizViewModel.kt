package com.example.movieapp;

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.QuestionCatalogue

class QuizViewModel : ViewModel() {
    val questions = QuestionCatalogue().defaultQuestions    // get a list of questions for the game
    var score = 0                                           // save the user's score
    var index = 0                                           // index for question data to show
    init {
        Log.i("QuizViewModel", "QuizViewModel created!")
    }
    fun increaseScore () {
        score++
    }
    fun increaseIndex () {
        index++
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("QuizViewModel", "QuizViewModel destroyed!")
    }
}
