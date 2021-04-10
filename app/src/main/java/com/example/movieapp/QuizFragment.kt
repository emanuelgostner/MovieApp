package com.example.movieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.movieapp.databinding.FragmentQuizBinding
import com.example.movieapp.models.Question
import com.example.movieapp.models.QuestionCatalogue


class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModel: QuizViewModel
    private var questions : MutableList<Question> = arrayListOf()
    private var score = 0
    private var index = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)

        Log.i("GameFragment", "QuizViewModel: Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        questions = viewModel.questions.value!!
        index = viewModel.index.value!!
        score = viewModel.score.value!!

        viewModel.questions.observe(viewLifecycleOwner, Observer { newQuestions ->
            binding.question = newQuestions[index]
            binding.questionsCount = newQuestions.size
        })

        viewModel.index.observe(viewLifecycleOwner, Observer { newIndex ->
            binding.index = newIndex
        })

        binding.btnNext.setOnClickListener { view : View ->
            nextQuestion(view)
        }

        return binding.root
    }

    private fun nextQuestion(v: View){
        var answer = binding.answerBox.checkedRadioButtonId

        //Toast message if no answer selected
        if(answer === -1) {
            Toast.makeText(activity, "Select an answer!",
                    Toast.LENGTH_SHORT).show()
            return
        }
        // get selected answer
        // check if is correct answer
        // update score
        if(answer === binding.answer1.id) {
            if(questions.get(index).answers.get(0).isCorrectAnswer)
                score++
        }
        if(answer === binding.answer2.id) {
            if(questions.get(index).answers.get(1).isCorrectAnswer)
                score++
        }
        if(answer === binding.answer3.id) {
            if(questions.get(index).answers.get(2).isCorrectAnswer)
                score++
        }
        if(answer === binding.answer4.id) {
            if(questions.get(index).answers.get(3).isCorrectAnswer)
                score++
        }
        // check if there are any viewModel.questions left
        if(questions.size-1 > index) {
            // show next question OR
            index++
            binding.index = index
            binding.question = questions[index]
        }
        else {
            // navigate to QuizEndFragment
            val scoreBundle = bundleOf("score" to score, "maxScore" to questions.size)
            v.findNavController().navigate(R.id.action_quizFragment_to_quizEndFragment, scoreBundle)
        }

    }
}