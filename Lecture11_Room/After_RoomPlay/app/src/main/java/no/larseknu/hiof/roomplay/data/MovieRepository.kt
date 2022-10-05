package no.larseknu.hiof.roomplay.data

class MovieRepository(private val movieDao: MovieDao) {

    fun getMovies() = movieDao.getAllMovies()

    fun getMovie(movieUid: Int) = movieDao.getMovie(movieUid)
}
