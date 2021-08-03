package demo.moviedb.data

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import demo.moviedb.data.database.CacheDatabase
import demo.moviedb.data.database.localCache.PopularLocalCache
import demo.moviedb.data.network.NetworkService
import demo.moviedb.data.repositories.MovieDetailsRepository
import demo.moviedb.data.repositories.PopularRepository
import demo.moviedb.ui.main.viewmodelfactory.ViewModelDetailFactory
import demo.moviedb.ui.main.viewmodelfactory.ViewModelPopularFactory
import java.util.concurrent.Executors

object Injection {

    private fun providePopularCache(context: Context): PopularLocalCache {
        val database = CacheDatabase.getInstance(context)
        return PopularLocalCache(database.poplarDao(), Executors.newSingleThreadExecutor())
    }

    private fun providePopularRepository(context: Context): PopularRepository {
        return PopularRepository(NetworkService.instance, providePopularCache(context))
    }

    fun providePopularViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelPopularFactory(providePopularRepository(context))
    }

    fun provideMovieDetailsRepository(context: Context): ViewModelProvider.Factory {
        val movieDetailsrepo = MovieDetailsRepository(providePopularCache(context))
        return ViewModelDetailFactory(movieDetailsrepo)
    }

}