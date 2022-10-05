package no.larseknu.hiof.viewmodelplay.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table ORDER BY title ASC")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie_table WHERE uid = :movieUid")
    fun getMovie(movieUid: Int): Movie

    @Insert
    fun insert(movie: Movie)

    @Query("DELETE FROM movie_table")
    fun deleteAll()
}