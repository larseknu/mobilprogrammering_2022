package no.hiof.larseknu.playingwithbottomnavigation.collectionscreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import no.hiof.larseknu.playingwithbottomnavigation.model.Movie
import no.larseknu.hiof.bottomnavigationplay.R

class CollectionFragment : Fragment() {
    private val movieList : List<Movie> = Movie.getMovies()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieRecyclerView = view.findViewById<RecyclerView>(R.id.movieRecyclerView)

        movieRecyclerView.adapter = MovieAdapter(movieList) {
            // Gets the position of the item that's clicked
            val position = movieRecyclerView.getChildAdapterPosition(it)

            // Gets the movie based on which item got clicked
            val clickedMovie = movieList[position]

            // Creates a toast with the movie that got clicked
            Toast.makeText(view.context, clickedMovie.title + " clicked", Toast.LENGTH_SHORT).show()
        }

        // Sets the layoutmanager we want to use
        movieRecyclerView.layoutManager = GridLayoutManager(context, 3)
    }
}
