package no.larseknu.hiof.roomplay.screens.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import no.larseknu.hiof.roomplay.MovieApplication
import no.larseknu.hiof.roomplay.databinding.FragmentMovieSelectionBinding


class MovieSelectionFragment : Fragment() {
    private val viewModel: MovieSelectionViewModel by viewModels {
        MovieSelectionViewModelFactory((activity?.application as MovieApplication).repository)
    }

    private var _binding: FragmentMovieSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieSelectionBinding.inflate(inflater, container, false)

        binding.selectionViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.movieList.observe(viewLifecycleOwner) {
            viewModel.showMovie()
        }

        viewModel.eventSelectionDone.observe(viewLifecycleOwner) { selectionDone ->
            if (selectionDone)
                movieSelectionFinished()
        }

        return binding.root
    }

    private fun movieSelectionFinished() {
        Toast.makeText(activity, "Movie selection finished", Toast.LENGTH_SHORT).show()
        val action = MovieSelectionFragmentDirections.actionMovieSelectionToSummary()
        action.numberAnswered = viewModel.numberAnswered.value?:0
        action.numberSeen = viewModel.numberSeen.value?:0
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}