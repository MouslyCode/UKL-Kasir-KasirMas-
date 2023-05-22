package com.example.kasirmas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirmas.databinding.RvCardMakananBinding

class AdapterFoodmas(private val context: Context, private val foodmass : ArrayList<Foodmas>, 
                     private val foodViewModel: MenuViewModel): RecyclerView.Adapter<AdapterFoodmas.ViewHolder>() {
    inner class ViewHolder(private val binding : RvCardMakananBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(foodmas: Foodmas){
            binding.cardMakananTvTitle.text = foodmas.name
            binding.cardMakananTvDesc.text = foodmas.description
            binding.cardMakananTvHarga.text = foodmas.price.toString()
            binding.cardMakananIvImage.setImageResource(R.drawable.ic_image)
            binding.cardMakananIvDelete.setOnClickListener {
                foodViewModel.deleteFoodmas(foodmas)
            }
        }
        fun onClick(context: Context, foodmas: Foodmas){
            binding.cardMakananIvUpdate.setOnClickListener {
                val intent = Intent(context, UpdateMenu::class.java)
                intent.putExtra("id", foodmas.id)
                intent.putExtra("menuName", foodmas.name)
                intent.putExtra("variety", "Makanan")
                intent.putExtra("desc", foodmas.description)
                intent.putExtra("price", foodmas.price.toString())
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardMakananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(foodmass[position])
        holder.onClick(context = context, foodmass[position])

    }

    override fun getItemCount(): Int {
        return foodmass.size
    }

    fun updateFoodmas(newList: List<Foodmas>){
        foodmass.clear()
        foodmass.addAll(newList)
        notifyDataSetChanged()
    }
}