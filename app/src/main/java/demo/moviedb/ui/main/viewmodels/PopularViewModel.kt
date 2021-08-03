package demo.moviedb.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import demo.moviedb.data.database.databaseResults.PopularResults
import demo.moviedb.data.database.entities.PopularEntry
import demo.moviedb.data.repositories.PopularRepository

class PopularViewModel(private val repository: PopularRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val queryLiveData = MutableLiveData<String>()
    private val popularResult: LiveData<PopularResults> = Transformations.map(queryLiveData, {
        repository.popular(it)
    })

    val recentData = MutableLiveData<List<PopularEntry>>()

    fun popularRecentVisitedResult() {
        recentData.value = repository.getAllPopularMovieVisitedInLastThirtyMinutes()
    }

    val nowshowing: LiveData<PagedList<PopularEntry>> = Transformations.switchMap(popularResult,
        { it -> it.data })
    val networkErrors: LiveData<String> = Transformations.switchMap(popularResult,
        { it -> it.networkErrors })

    fun getPopular(region: String) {
        queryLiveData.value = region
    }

}