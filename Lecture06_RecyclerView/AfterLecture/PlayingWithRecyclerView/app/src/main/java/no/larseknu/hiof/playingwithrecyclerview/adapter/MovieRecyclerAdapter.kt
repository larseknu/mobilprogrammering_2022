package no.larseknu.hiof.playingwithrecyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import no.larseknu.hiof.playingwithrecyclerview.R
import no.larseknu.hiof.playingwithrecyclerview.model.Movie


class MovieAdapter(private val items: List<Movie>, private val clickListener: View.OnClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // Called when there's a need for a new ViewHolder (a new item in the list/grid)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // Log so we can see when the onCreateViewHolder method is called
        Log.d("MovieAdapter", "Creating View")

        // Inflates the movie_list_item.xml to a view for us
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)

        // Create the viewholder with the corresponding view (list item)
        return MovieViewHolder(itemView)
    }

    // Called when data is bound to a specific ViewHolder (and item in the list/grid)
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // Log so we can see when the bind method is called
        Log.d("MovieAdapter", "Binding View $position")

        // Gets the movie data we are going to use at the given position
        val currentMovie = items[position]


        holder.bind(currentMovie, clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class MovieViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Gets a reference to all the specific views we are going to use or fill with data
        private val moviePosterImageView : ImageView = view.findViewById(R.id.moviePosterImageView)
        private val movieTitleTextView : TextView = view.findViewById(R.id.movieTitleTextView)

        fun bind(item: Movie, clickListener: View.OnClickListener) {
            // Fills the views with the given data
            moviePosterImageView.setImageResource(item.posterUrl)
            movieTitleTextView.text = item.title

            // Sets the onClickListener
            itemView.setOnClickListener(clickListener)
        }
    }
}