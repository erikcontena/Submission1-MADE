package com.contena.submission1.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide.*
import com.bumptech.glide.request.RequestOptions
import com.contena.core.domain.model.Catalog
import com.contena.submission1.BuildConfig
import com.contena.submission1.R
import com.contena.submission1.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "data"
        const val TYPE_MOVIE = 1
        const val TYPE_TVSHOW = 2
        const val TYPE = "type"

    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel  by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.statusBarColor = Color.TRANSPARENT

        val extras = intent.extras
        val data = intent.getParcelableExtra<Catalog>(EXTRA_DATA)
        extras?.getInt(TYPE)?.let { populateView(data, it) }

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

    }

    private fun populateView(detail: Catalog?, type: Int) {

        with(binding) {
            if (detail != null) {
                tvTitle.text = detail.title
                tvRelease.text = detail.release
                val score = detail.rating.toFloat() * 10

                circularProgressbar.progress = score.toInt()
                tvRating.text =
                    String.format(getString(R.string.progress_update), "${score.toInt()}")
                tvOverview.text = detail.description

                with(this@DetailActivity)
                    .load(BuildConfig.IMG_URL + detail.imagePath)
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_baseline_broken_image_24)
                    )
                    .into(imgPoster)

                with(this@DetailActivity)
                    .load(BuildConfig.IMG_URL + detail.bannerPath)
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_baseline_broken_image_24)
                    )
                    .into(imgBanner)
                var statusFavorite = detail.isFavorite

                setFavorit(statusFavorite)
                floatingActionButton.setOnClickListener {
                    statusFavorite = !statusFavorite
                    when (type) {
                        TYPE_MOVIE -> viewModel.setMovieFavorite(detail)
                        TYPE_TVSHOW -> viewModel.setTvShowFavorit(detail)
                    }
                    setFavorit(statusFavorite)
                }
            }
        }
    }
    private fun setFavorit(state: Boolean) {
        if (state) {
            binding.floatingActionButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorited
                )
            )
        } else {
            binding.floatingActionButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

}

