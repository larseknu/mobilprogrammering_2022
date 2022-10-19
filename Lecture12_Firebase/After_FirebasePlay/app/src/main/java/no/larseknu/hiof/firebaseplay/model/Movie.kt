package no.larseknu.hiof.firebaseplay.model

data class Movie(var uid : String = "", var title : String = "", var description : String = "", var posterUrl : String = "") {

    companion object {
        fun getMovies() : MutableList<Movie> {
            return listOf(
                Movie("0", "Captain America Civil War", "A cool movie!"),
                Movie("1", "Donnie Darko", "A cool movie!"),
                Movie("2", "Iron Man 3", "A cool movie!"),
                Movie("3", "Spirited Away", "A cool movie!"),
                Movie("4", "Star Wars The Force Awakens", "A cool movie!"),
                Movie("5", "The Hobbit", "A cool movie!"),
                Movie("6", "Up", "A cool movie!"),
                Movie("7", "Pulp Fiction", "A cool movie!"),
                Movie("8", "Coco", "A cool movie!"),
                Movie("9", "Deadpool", "A cool movie!"),
                Movie("10", "Inside Out", "A cool movie!"),
                Movie("11", "Into The Wild", "A cool movie!"),
                Movie("12", "The Hateful Eight", "A cool movie!"),
                Movie("13", "The Intouchables", "A cool movie!"),
                Movie("14", "The Lion King", "A cool movie!"),
                Movie("15", "Bo Burnham: Inside", "A cool movie!"),
                Movie("16", "Your Name", "A cool movie!"),
                Movie("17", "Spider-Man: No Way Home", "A cool movie!"),
            ) as MutableList<Movie>
        }
    }

}