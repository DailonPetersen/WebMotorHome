package com.example.webmotorhomeapp.ui.anuncios

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webmotorhomeapp.data.Image
import com.example.webmotorhomeapp.databinding.AnuncioChildLayoutBinding
import kotlin.math.roundToInt

class AnunciosChildAdapter(private val listaImages: List<Int>): RecyclerView.Adapter<AnunciosChildAdapter.AnuncioChildViewHolder>() {

    private var hasInitParentDimensions = false
    private var maxImageWidth: Int = 0
    private var maxImageHeight: Int = 0
    private var maxImageAspectRatio: Float = 1f

    inner class AnuncioChildViewHolder(binding: AnuncioChildLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val carousel: ImageView
        val textView: TextView

        init {
            carousel = binding.carousel
            textView = binding.titleAnuncio

        }
    }

    private lateinit var parentView: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioChildViewHolder {
        parentView = parent

        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = AnuncioChildLayoutBinding.inflate(layoutInflater, parent, false)

        return AnuncioChildViewHolder(itemView)
    }

    override fun getItemCount(): Int = listaImages.size

    override fun onBindViewHolder(holder: AnuncioChildViewHolder, position: Int) {
        val item = listaImages[position]

        holder.carousel.textAlignment = RecyclerView.TEXT_ALIGNMENT_TEXT_END

        holder.carousel.setImageResource(item)

    }
}