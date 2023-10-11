package com.example.webmotorhomeapp.ui.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.webmotorhomeapp.data.chat.Chat
import com.example.webmotorhomeapp.databinding.ChatLayoutBinding

class ListAdapter(private val list: List<Chat>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ChatLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val nome: TextView
        val conteudo: TextView

        init {
            nome = binding.chatName
            conteudo = binding.lastMessage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ChatLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.nome.text = item.nomeAnucio
        holder.conteudo.text = item.lastMessage
    }

}