package demo.moviedb.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import demo.moviedb.data.models.MovieDetail
import demo.moviedb.data.repositories.MovieDetailsRepository

class MovieDetailsViewModel(private val detailsRepo: MovieDetailsRepository) : ViewModel() {

    private var movieDetail: LiveData<MovieDetail>? = null

    fun getDetails(movieId: String): LiveData<MovieDetail> {
        if (movieDetail == null) {
            movieDetail = detailsRepo.getMovieDetails(movieId)
        }
        return movieDetail!!
    }
}