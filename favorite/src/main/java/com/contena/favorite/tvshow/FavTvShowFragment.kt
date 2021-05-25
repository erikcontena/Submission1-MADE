package com.contena.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.contena.core.adapter.CatalogAdapter
import com.contena.core.adapter.ClickItemCallback
import com.contena.core.domain.model.Catalog
import com.contena.favorite.FavoriteViewModel
import com.contena.favorite.databinding.FragmentFavTvShowBinding
import com.contena.favorite.favoriteModule
import com.contena.submission1.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavTvShowFragment : Fragment(), ClickItemCallback {
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var tvShowAdapter: CatalogAdapter
    private lateinit var binding: FragmentFavTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)
        tvShowAdapter = CatalogAdapter(this)

        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, {
            Log.d("TAG", "onViewCreated: ${it.size}")
            if (it.isEmpty()) {
                setView(rv = View.GONE, progress = View.GONE, error = View.VISIBLE)
            } else {
                setView(rv = View.VISIBLE, progress = View.GONE, error = View.GONE)
                tvShowAdapter.setData(it)
                tvShowAdapter.notifyDataSetChanged()
            }

        })


        with(binding.rvTvShow){
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    override fun <T> onClick(data: T) {
        data as Catalog
        Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DATA, data)
            putExtra(DetailActivity.TYPE, DetailActivity.TYPE_TVSHOW )
            startActivity(this)
        }
    }
    private fun setView(rv: Int, progress: Int, error: Int) {
        binding.progressBar.visibility = progress
        binding.rvTvShow.visibility = rv
        binding.imgError.visibility = error
        binding.tvEmpty.visibility = error
    }
}