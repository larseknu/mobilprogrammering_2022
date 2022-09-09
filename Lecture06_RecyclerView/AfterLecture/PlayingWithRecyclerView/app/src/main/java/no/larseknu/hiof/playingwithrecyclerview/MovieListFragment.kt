package no.larseknu.hiof.playingwithrecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import no.larseknu.hiof.playingwithrecyclerview.adapter.MovieAdapter
import no.larseknu.hiof.playingwithrecyclerview.model.Movie


class MovieListFragment : Fragment() {
    private val movieList : List<Movie> = Movie.getMovies()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieRecyclerView = view.findViewById<RecyclerView>(R.id.movieRecyclerView)

        movieRecyclerView.adapter = MovieAdapter(movieList) {
            // Gets the position of the item that's clicked
            val position = movieRecyclerView.getChildAdapterPosition(it)

            // Gets the movie based on which item got clicked
            val clickedMovie = movieList[position]

            // Creates the navigation action, including the uid argument
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment()
            action.uid = clickedMovie.uid

            // Calls the navigate action, taking us to the MovieDatilFragment
            findNavController().navigate(action)

            // Creates a toast with the movie that got clicked
            Toast.makeText(view.context, clickedMovie.title + " clicked", Toast.LENGTH_SHORT).show()
        }

        // Sets the layoutmanager we want to use
        movieRecyclerView.layoutManager = GridLayoutManager(context, 3)
        //movieRecyclerView.layoutManager = GridLayoutManager(context, 2)
        //movieRecyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        //movieRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        //movieRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //movieRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}