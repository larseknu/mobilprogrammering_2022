package no.larseknu.hiof.roomplay.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import no.larseknu.hiof.roomplay.R

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): MovieDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, scope).also { instance = it}
            }
        }

        private fun buildDatabase(context: Context, scope: CoroutineScope): MovieDatabase {
            return Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database")
                .addCallback(MovieDatabaseCallback(scope))
                .build()
        }
    }

    private class MovieDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            instance?.let { database ->
                scope.launch {
                    val movieDao = database.movieDao()

                    // Delete all content here.
                    movieDao.deleteAll()

                    // Add sample words.
                    movieDao.insert(Movie("Captain America Civil War", "A cool movie!", R.drawable.captain_america_civil_war))
                    movieDao.insert(Movie("Donnie Darko", "A cool movie!", R.drawable.donnie_darko))
                    movieDao.insert(Movie("Iron Man 3", "A cool movie!", R.drawable.iron_man_3))
                    movieDao.insert(Movie("Spirited Away", "A cool movie!", R.drawable.spirited_away))
                    movieDao.insert(Movie("Star Wars The Force Awakens", "A cool movie!", R.drawable.star_wars_the_force_awakens))
                    movieDao.insert(Movie("The Hobbit", "A cool movie!", R.drawable.the_hobbit))
                    movieDao.insert(Movie("Up", "A cool movie!", R.drawable.up))
                    movieDao.insert(Movie("Pulp Fiction", "A cool movie!", R.drawable.pulp_fiction))
                    movieDao.insert(Movie("Coco", "A cool movie!", R.drawable.coco))
                    movieDao.insert(Movie("Deadpool", "A cool movie!", R.drawable.deadpool))
                    movieDao.insert(Movie("Inside Out", "A cool movie!", R.drawable.inside_out))
                    movieDao.insert(Movie("Into The Wild", "A cool movie!", R.drawable.into_the_wild))
                    movieDao.insert(Movie("The Hateful Eight", "A cool movie!", R.drawable.the_hateful_eight))
                    movieDao.insert(Movie("The Intouchables", "A cool movie!", R.drawable.the_intouchables))
                    movieDao.insert(Movie("The Lion King", "A cool movie!", R.drawable.the_lion_king))
                    movieDao.insert(Movie("Bo Burnham: Inside", "A cool movie!", R.drawable.inside))
                    movieDao.insert(Movie("Your Name", "A cool movie!", R.drawable.your_name))
                    movieDao.insert(Movie("Spider-Man: No Way Home", "A cool movie!", R.drawable.spiderman_no_way_home))
                }        }
        }
    }
}