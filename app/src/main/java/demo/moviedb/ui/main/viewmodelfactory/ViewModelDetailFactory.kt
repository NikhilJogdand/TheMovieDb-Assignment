package demo.moviedb.ui.main.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import demo.moviedb.data.repositories.MovieDetailsRepository
import demo.moviedb.ui.main.viewmodels.MovieDetailsViewModel


class ViewModelDetailFactory(private val repository: MovieDetailsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}