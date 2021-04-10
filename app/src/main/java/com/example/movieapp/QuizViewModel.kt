package com.example.movieapp;

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Question
import com.example.movieapp.models.QuestionCatalogue

class QuizViewModel : ViewModel() {
    val questions = MutableLiveData<MutableList<Question>>()    // get a list of questions for the game
    var score = MutableLiveData<Int>()                 // save the user's score
    var index = MutableLiveData<Int>()                  // index for question data to show
    init {
        Log.i("QuizViewModel", "QuizViewModel created!")
        questions.value = QuestionCatalogue().defaultQuestions
        score.value = 0
        index.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("QuizViewModel", "QuizViewModel destroyed!")
    }
}
