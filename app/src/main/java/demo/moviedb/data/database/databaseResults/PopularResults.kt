package demo.moviedb.data.database.databaseResults

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import demo.moviedb.data.database.entities.PopularEntry

data class PopularResults(
    val data: LiveData<PagedList<PopularEntry>>,
    val networkErrors: LiveData<String>
)