package com.contena.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.contena.core.BuildConfig
import com.contena.core.R
import com.contena.core.databinding.ItemMovieBinding
import com.contena.core.domain.model.Catalog
import java.util.*

class CatalogAdapter(private val callback: ClickItemCallback) : RecyclerView.Adapter<CatalogAdapter.ListViewHolder>() {

    private var listData = ArrayList<Catalog>()
    fun setData(newListData: List<Catalog>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogAdapter.ListViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemMovieBinding)
    }
    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: CatalogAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Catalog){
            with(binding){
                tvTitle.text = movie.title
                tvRelease.text = movie.release


                val score = movie.rating.toFloat()*10
                circularProgressbar.progress = score.toInt()
                tvRating.text = String.format(itemView.context.getString(R.string.progress_update),"${score.toInt()}" )
                Glide.with(itemView.context)
                    .load(BuildConfig.IMG_URL+movie.imagePath)
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_baseline_broken_image_24))
                    .into(imgPoster)

                itemView.setOnClickListener{
                    callback.onClick(movie)
                }
            }
        }

    }
}