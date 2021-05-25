package com.contena.submission1.ui.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.contena.core.adapter.CatalogAdapter
import com.contena.core.adapter.ClickItemCallback
import com.contena.core.data.source.remote.network.ApiResponse
import com.contena.core.domain.model.Catalog
import com.contena.core.utils.Resource
import com.contena.core.utils.SortUtils
import com.contena.submission1.R
import com.contena.submission1.databinding.FragmentMovieBinding
import com.contena.submission1.ui.detail.DetailActivity
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class MovieFragment : Fragment(), ClickItemCallback {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: CatalogAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = CatalogAdapter(this)

        viewModel.getMovies(SortUtils.ORIGINAL).observe(viewLifecycleOwner, dataObserver)



        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter

        }

        binding.floatingActionButton.setOnClickListener {
            val uri = Uri.parse("tmdbapp://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        val popupMenu = PopupMenu(context, binding.filter)
        popupMenu.inflate(R.menu.main_menu)
        binding.filter.setOnClickListener {
            popupMenu.setOnMenuItemClickListener { item ->
                val sort = if (item.itemId == R.id.original) {
                    SortUtils.ORIGINAL
                } else {
                    SortUtils.RANDOM
                }
                item.isChecked = !item.isChecked
                viewModel.getMovies(sort).observe(viewLifecycleOwner, dataObserver)
                true
            }
            popupMenu.show()
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") {
                    viewModel.getMovies(SortUtils.ORIGINAL).observe(viewLifecycleOwner, dataObserver)
                }else{
                    lifecycleScope.launch {
                        viewModel.searchResult(newText)
                            .observe(viewLifecycleOwner,{
                                when(it){
                                    is ApiResponse.Empty ->{
                                        setView(rv = View.GONE, progress = View.GONE, error = View.VISIBLE)
                                        binding.tvError.text = resources.getString(R.string.empty)
                                    }
                                    is ApiResponse.Success ->{
                                        setView(rv = View.VISIBLE, progress = View.GONE, error = View.GONE)
                                        movieAdapter.setData(it.data)
                                        movieAdapter.notifyDataSetChanged()
                                    }
                                    is ApiResponse.Error ->
                                        setView(rv = View.GONE, progress = View.GONE, error = View.VISIBLE)
                                }
                            })
                    }
                }
                return false
            }

        })


    }


    override fun <T> onClick(data: T) {
        data as Catalog
        Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DATA, data)
            putExtra(DetailActivity.TYPE, DetailActivity.TYPE_MOVIE)
            startActivity(this)
        }
    }


    private val dataObserver = Observer<Resource<List<Catalog>>> { resource ->
        when (resource) {
            is Resource.Success -> {
                if (resource.data?.isEmpty() == true) {
                    setView(rv = View.GONE, progress = View.GONE, error = View.VISIBLE)
                    binding.tvError.text = resources.getString(R.string.empty)
                } else {
                    setView(rv = View.VISIBLE, progress = View.GONE, error = View.GONE)
                    movieAdapter.setData(resource.data)
                    movieAdapter.notifyDataSetChanged()
                }
            }
            is Resource.Error -> {
                setView(rv = View.GONE, progress = View.GONE, error = View.VISIBLE)
                Toast.makeText(requireActivity(), resource.message, Toast.LENGTH_LONG).show()
            }
            is Resource.Loading -> {
                setView(rv = View.GONE, progress = View.VISIBLE, error = View.GONE)
            }
        }

    }

    private fun setView(rv: Int, progress: Int, error: Int) {
        binding.progressBar.visibility = progress
        binding.rvMovie.visibility = rv
        binding.imgError.visibility = error
        binding.tvError.visibility = error
    }


}