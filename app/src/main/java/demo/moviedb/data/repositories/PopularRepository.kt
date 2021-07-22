package demo.moviedb.data.repositories

import androidx.paging.LivePagedListBuilder
import demo.moviedb.data.database.databaseResults.PopularResults
import demo.moviedb.data.database.localCache.PopularLocalCache
import demo.moviedb.data.network.NetworkService
import demo.moviedb.ui.base.boundaryCallbacks.PopularBoundaryCallbacks


class PopularRepository(
    private val service: NetworkService,
    private val popularCache: PopularLocalCache
) {

    fun popular(region: String): PopularResults {

        val dataSourceFactory = popularCache.getAllPopular()

        val boundaryCallback = PopularBoundaryCallbacks(region, service, popularCache)
        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
        return PopularResults(data, networkErrors)
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 50
        private const val DATABASE_PAGE_SIZE = 60
    }

}