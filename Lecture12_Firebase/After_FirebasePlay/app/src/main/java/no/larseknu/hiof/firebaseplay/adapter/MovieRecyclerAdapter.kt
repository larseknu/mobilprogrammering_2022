package no.larseknu.hiof.firebaseplay.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import no.larseknu.hiof.firebaseplay.MovieListFragmentDirections
import no.larseknu.hiof.firebaseplay.R
import no.larseknu.hiof.firebaseplay.databinding.MovieListItemBinding
import no.larseknu.hiof.firebaseplay.model.Movie

class MovieAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as MovieViewHolder).bind(movie)
    }


    class MovieViewHolder(private val binding: MovieListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                Toast.makeText(binding.movieTitleTextView.context, binding.movie?.title + "", Toast.LENGTH_SHORT).show()

                val direction = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment()
                direction.uid = binding.movie!!.uid

                it.findNavController().navigate(direction)
            }
        }

        fun bind(item: Movie) {
            binding.apply {
                movie = item
                executePendingBindings()
            }
            if (item.posterUrl.isNotEmpty()) {
                // Gets the movie with the uid (just a number in the list in this case)
                val reference =  Firebase.storage.getReference("/movie_posters/").child(item.posterUrl)

                Glide.with(binding.root.context)
                    .load(reference)
                    .into(binding.moviePosterImageView)
            }
            else
                binding.moviePosterImageView.setImageResource(R.drawable.poster_placeholder)
        }
    }
}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}