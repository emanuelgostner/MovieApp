package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.movieapp.databinding.FragmentQuizEndBinding


class QuizEndFragment : Fragment() {
    private lateinit var binding: FragmentQuizEndBinding
    private lateinit var viewModel: QuizEndViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_end, container, false)

        // get score from navigation arguments
        Log.i("GameFragment", "QuizViewModel: Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(QuizEndViewModel::class.java)
        viewModel.score.value = arguments?.getInt("score")!!
        viewModel.maxScore.value = arguments?.getInt("maxScore")!!

        // show score
        binding.score.text = String.format("%d / %d", viewModel.score.value, viewModel.maxScore.value)

        //button
        binding.retryButton.setOnClickListener { view : View ->
            restartQuiz(view)
        }
        return binding.root
    }

    private fun restartQuiz(v: View) {
        v.findNavController().navigate(R.id.action_quizEndFragment_to_quizFragment)
    }
}