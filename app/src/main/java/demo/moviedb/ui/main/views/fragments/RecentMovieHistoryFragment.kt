package demo.moviedb.ui.main.views.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import demo.moviedb.R
import demo.moviedb.data.Injection
import demo.moviedb.data.database.entities.PopularEntry
import demo.moviedb.data.models.Movie
import demo.moviedb.ui.base.interfaces.OnMovieClickListener
import demo.moviedb.ui.main.adapters.MovieAdapter
import demo.moviedb.ui.main.viewmodels.PopularViewModel
import demo.moviedb.ui.main.views.activities.MoviesActivity

class RecentMovieHistoryFragment : Fragment(), OnMovieClickListener {

    private val GRID_COLUMNS_PORTRAIT = 1
    private val GRID_COLUMNS_LANDSCAPE = 2
    private lateinit var mMainView: View
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var emptyList: TextView
    private lateinit var viewModel: PopularViewModel
    lateinit var mMovieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mMainView = inflater.inflate(R.layout.fragment_recent_movie_history, container, false)
        initViews()
        initRecyclerView()
        return mMainView
    }

    override fun onResume() {
        super.onResume()
        setToolbarTittle()
    }

    private fun setToolbarTittle() {
        (activity as MoviesActivity).supportActionBar?.title = "Recent Visited Movies"
    }

    private fun initViews() {
        mRecyclerView = mMainView.findViewById(R.id.fragment_popular_movies_recycler_view)
        emptyList = mMainView.findViewById(R.id.emptyPopularList)
    }

    private fun initRecyclerView() {
        configureRecyclerAdapter(resources.configuration.orientation)
        viewModel = ViewModelProviders.of(this, Injection.providePopularViewModelFactory(context!!))
            .get(PopularViewModel::class.java)

        viewModel.recentData.observe(this, Observer<List<PopularEntry>> {
            showEmptyList(it?.size == 0)
            mMovieAdapter = MovieAdapter(it)
            mRecyclerView.adapter = mMovieAdapter
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show()
        })
        viewModel.popularRecentVisitedResult()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        configureRecyclerAdapter(newConfig.orientation)
    }

    private fun configureRecyclerAdapter(orientation: Int) {
        val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT
        mGridLayoutManager = GridLayoutManager(
            context,
            if (isPortrait) GRID_COLUMNS_PORTRAIT else GRID_COLUMNS_LANDSCAPE
        )
        mRecyclerView.layoutManager = mGridLayoutManager
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            mRecyclerView.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            mRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onMovieClickListener(movie: Movie) {

    }

}