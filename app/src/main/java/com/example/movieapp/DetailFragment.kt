package com.example.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


@BindingAdapter("android:text")
fun setText(view: TextView, list: MutableList<String>) {
    view.text = list.joinToString(", ")
}

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail, container, false)

        //bind click Handler
        binding.fabButton.setOnClickListener {
            clickHandlerFunction(it)
        }

        //bind object instance to layout
        binding.movie = Movie();

        return binding.root
    }

    private fun clickHandlerFunction(viewThatIsClicked: View) {
       /* Toast.makeText(this, "button clicked",
            Toast.LENGTH_SHORT).show()*/
    }
}