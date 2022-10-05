package no.larseknu.hiof.viewmodelplay.screens.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.NavHostFragment
import no.larseknu.hiof.viewmodelplay.R
import no.larseknu.hiof.viewmodelplay.databinding.FragmentMovieSelectionBinding


class MovieSelectionFragment : Fragment() {
    private var _binding: FragmentMovieSelectionBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MovieSelectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieSelectionBinding.inflate(inflater, container, false)
        binding.selectionViewModel = viewModel
        binding.lifecycleOwner = this

        // Not needed when we have set it up with binding to the ViewModel
        //binding.seenButton.setOnClickListener { viewModel.seenMovie() }
        //binding.notSeenButton.setOnClickListener { viewModel.notSeenMovie() }
        binding.doneButton.setOnClickListener { movieSelectionFinished() }

        viewModel.selectionDone.observe(viewLifecycleOwner) { selectionDone ->
            if (selectionDone)
                movieSelectionFinished()
        }

        //updateMovieInUI()

        return binding.root
    }

    private fun movieSelectionFinished() {
        Toast.makeText(activity, "Movie selection finished", Toast.LENGTH_SHORT).show()
        val action = MovieSelectionFragmentDirections.actionMovieSelectionToSummary()
        action.numberAnswered = viewModel.numberAnswered.value?:0
        action.numberSeen = viewModel.numberSeen.value?:0
        NavHostFragment.findNavController(this).navigate(action)
    }



    /**
    * These are not needed when we use an observer
    */
    /*private fun updateMovieInUI() {
        binding.numberSeenOfAnsweredText.text = getString(R.string.number_seen_of_answered, viewModel.numberSeen.value?:0, viewModel.numberAnswered.value?:0)
        binding.movieTitle.text = viewModel.currentMovie.value?.title
        binding.moviePoster.setImageResource(viewModel.currentMovie.value?.posterUrl!!)
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}