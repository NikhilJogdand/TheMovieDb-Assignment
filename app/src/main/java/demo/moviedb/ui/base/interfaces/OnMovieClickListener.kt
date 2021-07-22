package demo.moviedb.ui.base.interfaces

import demo.moviedb.data.models.Movie

interface OnMovieClickListener {
    fun onMovieClickListener(movie: Movie)
}