package com.contena.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.contena.core.adapter.CatalogAdapter
import com.contena.core.adapter.ClickItemCallback
import com.contena.core.domain.model.Catalog
import com.contena.favorite.FavoriteViewModel
import com.contena.favorite.databinding.FragmentFavMovieBinding
import com.contena.favorite.favoriteModule
import com.contena.submission1.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavMovieFragment : Fragment(), ClickItemCallback {

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var movieAdapter: CatalogAdapter
    private lateinit var binding: FragmentFavMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)

        movieAdapter = CatalogAdapter(this)

        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                setView(rv = View.GONE, progress = View.GONE, error = View.VISIBLE)
            } else {
                setView(rv = View.VISIBLE, progress = View.GONE, error = View.GONE)
                movieAdapter.setData(it)
                movieAdapter.notifyDataSetChanged()
            }
        })


        with(receiver = binding.rvMovie){
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun <T> onClick(data: T) {
        data as Catalog
        Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DATA, data)
            putExtra(DetailActivity.TYPE, DetailActivity.TYPE_MOVIE)
            startActivity(this)
        }
    }
    private fun setView(rv: Int, progress: Int, error: Int) {
        binding.progressBar.visibility = progress
        binding.rvMovie.visibility = rv
        binding.imgError.visibility = error
        binding.tvEmpty.visibility = error
    }

}