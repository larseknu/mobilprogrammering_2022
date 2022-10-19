package no.larseknu.hiof.firebaseplay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import no.larseknu.hiof.firebaseplay.adapter.MovieAdapter
import no.larseknu.hiof.firebaseplay.databinding.FragmentMovieDetailBinding
import no.larseknu.hiof.firebaseplay.databinding.FragmentMovieListBinding
import no.larseknu.hiof.firebaseplay.model.Movie

class MovieListFragment : Fragment() {
    private val movieList : List<Movie> = Movie.getMovies()

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieAdapter()
        binding.movieRecyclerView.adapter = adapter

        adapter.submitList(Movie.getMovies())

        // Sets the layoutmanager we want to use
        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 3)
    }
}