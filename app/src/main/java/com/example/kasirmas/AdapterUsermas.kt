package com.example.kasirmas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirmas.databinding.RvCardDatauserBinding
import com.example.kasirmas.databinding.RvCardMejamasBinding

class AdapterUsermas (private val context: Context,
                      private val usermas : ArrayList<Usermas>,
                      private val usermasViewModel: UsermasViewModel): RecyclerView.Adapter<AdapterUsermas.ViewHolder>() {
    inner class ViewHolder(private val binding : RvCardDatauserBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(usermas: Usermas){
            binding.cardUserTvTitle.text = usermas.name
            binding.cardUserTvDesc.text = usermas.job
            binding.cardUserIvImage.setImageResource(R.drawable.ic_image)
            binding.cardUserIvDelete.setOnClickListener {
                usermasViewModel.deleteUsermas(usermas)
            }
        }
        fun onClick(context: Context, usermas: Usermas){
            binding.cardUserIvUpdate.setOnClickListener {
                val intent = Intent(context, UpdateUsermas::class.java)
                intent.putExtra("id", usermas.id)
                intent.putExtra("name", usermas.name)
                intent.putExtra("userName", usermas.username)
                intent.putExtra("password", usermas.password)
                intent.putExtra("jobdesc", usermas.job)
                context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterUsermas.ViewHolder {
        val binding = RvCardDatauserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(usermas[position])
        holder.onClick(context = context, usermas[position])

    }

    override fun getItemCount(): Int {
        return usermas.size
    }

    fun updateFoodmas(newList: List<Usermas>){
        usermas.clear()
        usermas.addAll(newList)
        notifyDataSetChanged()
    }

}