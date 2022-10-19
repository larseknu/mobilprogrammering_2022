package no.larseknu.hiof.firebaseplay

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import no.larseknu.hiof.firebaseplay.adapter.MovieAdapter
import no.larseknu.hiof.firebaseplay.databinding.FragmentMovieListBinding
import no.larseknu.hiof.firebaseplay.model.Movie


class MovieListFragment : Fragment() {
    private val movieList : MutableList<Movie> = mutableListOf()
    private val movieUidList : MutableList<String> = mutableListOf()
    private val movieAdapter = MovieAdapter()

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieCollectionReference: CollectionReference
    private lateinit var fireStoreListenerRegistration: ListenerRegistration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        val db = Firebase.firestore
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
        db.firestoreSettings = settings

        movieCollectionReference = Firebase.firestore.collection("movies")

        //generateTestData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieRecyclerView.adapter = movieAdapter
        movieAdapter.submitList(movieList)

        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 3)

        binding.floatingActionButton.setOnClickListener {
            movieCollectionReference.add(Movie("999", "Dumb and Dumber", "A dumb movie!"))
        }

    }


    override fun onResume() {
        super.onResume()


        createFireStoreReadListener()
    }

    override fun onPause() {
        super.onPause()

        fireStoreListenerRegistration.remove()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun createFireStoreReadListener() {
        // If you only want to retrieve data once, without updates later on
        // you can use an onCompleteListener on the collectionreference
        /*movieCollectionReference.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                movieList.clear()
                for (documentSnapShot in task.result!!) {
                    val movie = documentSnapShot.toObject(Movie::class.java)
                    movieList.add(movie)
                }

                binding.movieRecyclerView.adapter?.notifyDataSetChanged()
            } else {
                Log.d("Something", "Error getting documents: " + task.exception)
            }
        }*/


        // If you want to receive updates to the data, add a listener
        fireStoreListenerRegistration = movieCollectionReference.addSnapshotListener { querySnapshot, exception ->
            if (exception != null) {
                Log.w("LOGTAG", "Listen failed.", exception)
                return@addSnapshotListener
            }

            for (documentChange in querySnapshot?.documentChanges!!) {
                val documentSnapshot = documentChange.document
                val movie = documentSnapshot.toObject<Movie>()
                movie.uid = documentSnapshot.id

                val pos = movieUidList.indexOf(movie.uid)

                when (documentChange.type) {
                    DocumentChange.Type.ADDED -> {
                        if (!movieUidList.contains(movie.uid)) {
                            movieList.add(movie)
                            movieUidList.add(movie.uid)
                            movieAdapter.notifyItemInserted(movieList.size - 1)
                        }
                    }
                    DocumentChange.Type.REMOVED -> {
                        movieList.removeAt(pos)
                        movieUidList.removeAt(pos)
                        movieAdapter.notifyItemRemoved(pos)
                    }
                    DocumentChange.Type.MODIFIED -> {
                        movieList[pos] = movie
                        movieAdapter.notifyItemChanged(pos)
                    }
                }
            }
        }
    }

    private fun generateTestData() {
        for (aMovie in Movie.getMovies()) {
            movieCollectionReference.add(aMovie)
                .addOnSuccessListener { documentReference ->
                    Log.d("AddToDatabaseSucc", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("AddToDatabaseErr", "Error adding document", e)
                }
        }
    }
}