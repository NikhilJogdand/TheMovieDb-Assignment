package demo.moviedb.ui.base.interfaces

import demo.moviedb.data.models.MovieVideo

interface OnVideoClickListener {
    fun onVideoClickListener(movieVideo: MovieVideo)
}