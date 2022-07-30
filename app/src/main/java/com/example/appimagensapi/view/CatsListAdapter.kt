package com.example.appimagensapi.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.appimagensapi.R
import com.example.appimagensapi.model.ImagesDto



class CatsListAdapter() :
    ListAdapter<ImagesDto, CatsListAdapter.RecipeViewHolder>(CatsListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_grid_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val cats = getItem(position)
        holder.bind()
        holder.txtTitle.text = cats.data
        holder.imgCats.load(cats.link) {
            crossfade(true)
            placeholder(R.drawable.ic_place_holder)
            transformations(RoundedCornersTransformation(topLeft = 15f, topRight = 15f))
        }
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var txtTitle: TextView
        lateinit var imgCats: ImageView



        fun bind() {
            with(itemView) {
                txtTitle = findViewById(R.id.tv_card)
                imgCats = findViewById(R.id.iv_card)
            }
        }
    }


    private companion object : DiffUtil.ItemCallback<ImagesDto>() {

        override fun areItemsTheSame(oldItem: ImagesDto, newItem: ImagesDto): Boolean {
            return oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: ImagesDto, newItem: ImagesDto): Boolean {
            return oldItem == newItem
        }
    }

}