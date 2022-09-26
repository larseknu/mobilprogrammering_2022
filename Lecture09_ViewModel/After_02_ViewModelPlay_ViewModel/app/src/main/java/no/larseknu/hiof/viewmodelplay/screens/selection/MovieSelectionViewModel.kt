package no.larseknu.hiof.viewmodelplay.screens.selection

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import no.larseknu.hiof.viewmodelplay.model.Movie

class MovieSelectionViewModel : ViewModel() {

    private lateinit var movieList : ArrayList<Movie>
    lateinit var currentMovie: Movie
    var numberAnswered = 0
    var numberSeen = 0

    init {
        numberAnswered = 0
        numberSeen = 0

        retrieveList()
        nextMovie()
        Log.i("GameViewModel", "GameViewModel created!")
    }

    fun seenMovie() {
        numberSeen++
        numberAnswered++

        nextMovie()
    }

    fun notSeenMovie() {
        numberAnswered++

        nextMovie()
    }

    private fun nextMovie() {
        if (movieList.isNotEmpty()) {
            currentMovie = movieList.removeAt(0)
        }
    }

    private fun retrieveList() {
        movieList = ArrayList(Movie.getMovies())
        movieList.shuffle()
    }

}