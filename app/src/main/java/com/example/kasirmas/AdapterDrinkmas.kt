package com.example.kasirmas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirmas.databinding.RvCardMinumanBinding

class AdapterDrinkmas (private val context: Context, private val drinkmas : ArrayList<Drinkmas>,
                       private val drinkmasViewModel: MenuViewModel): RecyclerView.Adapter<AdapterDrinkmas.ViewHolder>() {
    inner class ViewHolder(private val binding : RvCardMinumanBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(drinkmas: Drinkmas){
            binding.cardMinumanTvTitle.text = drinkmas.name
            binding.cardMinumanTvDesc.text = drinkmas.description
            binding.cardMinumanTvHarga.text = drinkmas.price.toString()
            binding.cardMinumanIvImage.setImageResource(R.drawable.ic_image)
            binding.cardMinumanIvDelete.setOnClickListener {
                drinkmasViewModel.deleteDrinkmas(drinkmas)
            }

        }

        fun onClick(context: Context, drinkmas: Drinkmas){
            binding.cardMinumanIvUpdate.setOnClickListener {
                val intent = Intent(context, UpdateMenu::class.java)
                intent.putExtra("id", drinkmas.id)
                intent.putExtra("menuName", drinkmas.name)
                intent.putExtra("variety", "Minuman")
                intent.putExtra("desc", drinkmas.description)
                intent.putExtra("price", drinkmas.price.toString())
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardMinumanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(drinkmas[position])
        holder.onClick(context = context, drinkmas[position])
    }

    override fun getItemCount(): Int {
        return drinkmas.size
    }

    fun updateDrinkmas(newList: List<Drinkmas>){
        drinkmas.clear()
        drinkmas.addAll(newList)
        notifyDataSetChanged()
    }
}
