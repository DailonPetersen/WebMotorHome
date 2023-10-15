package com.example.webmotorhomeapp.ui.anuncios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import androidx.recyclerview.widget.RecyclerView.TEXT_ALIGNMENT_TEXT_END
import com.example.webmotorhomeapp.data.Image
import com.example.webmotorhomeapp.data.anuncio.Anuncio
import com.example.webmotorhomeapp.databinding.AnuncioLayoutBinding


class AnunciosAdapter(private val listaAnuncios: List<Anuncio>): RecyclerView.Adapter<AnunciosAdapter.AnuncioViewHolder>() {

    inner class AnuncioViewHolder(binding: AnuncioLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        var recyclerView: RecyclerView

        init {
            recyclerView = binding.childRecyclerView
        }
    }

    private lateinit var parentView: ViewGroup
    private val viewPool = RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioViewHolder {
        parentView = parent

        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = AnuncioLayoutBinding.inflate(layoutInflater, parent, false)

        return AnuncioViewHolder(itemView)
    }

    override fun getItemCount(): Int = listaAnuncios.size

    override fun onBindViewHolder(holder: AnuncioViewHolder, position: Int) {
        val item = listaAnuncios[position]

        holder.recyclerView.textAlignment = TEXT_ALIGNMENT_TEXT_END

        val layoutManager = LinearLayoutManager(
            parentView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        item.images?.size?.let { layoutManager.initialPrefetchItemCount = it };

//        val list = mutableListOf<Image>()
//        item.images?.let {
//            for (image in it) {
//                list.add(Image(image, 356, 200, item.descricao.substring(0, 10)))
//            }
//        }

        val childItemAdapter = item.images?.let { AnunciosChildAdapter(it) }

        holder.recyclerView.layoutManager = layoutManager
        holder.recyclerView.adapter = childItemAdapter
        holder.recyclerView.setRecycledViewPool(viewPool)
    }
}