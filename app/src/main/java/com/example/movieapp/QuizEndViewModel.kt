package com.example.movieapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.QuestionCatalogue

class QuizEndViewModel : ViewModel() {
    var score = MutableLiveData<Int>()
    var maxScore = MutableLiveData<Int>()
    init {
        Log.i("QuizEndViewModel", "QuizEndViewModel created!")
        score.value = 0
        maxScore.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("QuizEndViewModel", "QuizEndViewModel destroyed!")
    }
}
