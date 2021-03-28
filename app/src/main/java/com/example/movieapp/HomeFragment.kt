package com.example.movieapp

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        //bind static movieItems
        binding.itemButton1.setOnClickListener { view : View ->
            val movie = Movie()
            movie.title = "The Queens Gambit"
            val movieBundle = Bundle()
            movieBundle.putSerializable("movie", movie)
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment, movieBundle)
        }
        binding.itemButton2.setOnClickListener { view : View ->
            val movie = Movie()
            movie.title = "Test movie 2"
            val movieBundle = Bundle()
            movieBundle.putSerializable("movie", movie)
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment, movieBundle)
        }
        binding.itemButton3.setOnClickListener { view : View ->
            val movie = Movie()
            movie.title = "Test movie 3"
            val movieBundle = Bundle()
            movieBundle.putSerializable("movie", movie)
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment, movieBundle)
        }

        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}