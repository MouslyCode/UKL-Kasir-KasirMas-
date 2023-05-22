package com.example.kasirmas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirmas.databinding.RvCardMejamasBinding

class AdapterMejamas (private val context: Context, private val tables : ArrayList<Mejamas>,
                      private val mejaViewModel: MejamasViewModel): RecyclerView.Adapter<AdapterMejamas.ViewHolder>(){
    inner class ViewHolder(private val binding : RvCardMejamasBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(mejamas: Mejamas){
            binding.cardTableTvTitle.text = "Meja "+mejamas.number.toString()
            binding.cardTableIvDelete.setOnClickListener {
                mejaViewModel.deleteTable(mejamas)
            }
        }
        fun onClick(context: Context, mejamas: Mejamas){
            binding.cardTableIvUpdate.setOnClickListener {
                val intent = Intent(context, UpdateMejamas::class.java)
                intent.putExtra("id", mejamas.id)
                intent.putExtra("noMeja", mejamas.number)
                context.startActivity(intent)
            }
        }
    }

    fun updateTable(newList: List<Mejamas>){
        tables.clear()
        tables.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardMejamasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tables[position])
        holder.onClick(context = context, tables[position])
    }

    override fun getItemCount(): Int {
        return tables.size
    }
}