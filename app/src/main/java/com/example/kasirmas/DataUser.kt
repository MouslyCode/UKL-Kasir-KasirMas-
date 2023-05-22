package com.example.kasirmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirmas.databinding.ActivityDataUserBinding

class DataUser : AppCompatActivity() {
    private lateinit var binding : ActivityDataUserBinding
    private lateinit var usermasViewModel: UsermasViewModel
    private var usermas = ArrayList<Usermas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        usermasViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UsermasViewModel::class.java]

        initUserRv()

        binding.dataUserIbBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.dataUserFabAdd.setOnClickListener {
            startActivity(Intent(this, AddUsermas::class.java))
            finish()
        }
    }

    private fun initUserRv(){


        val Adapteruser = AdapterUsermas(this, usermas, usermasViewModel)

        val rvUser = binding.dataUserRv
        rvUser.setHasFixedSize(true)
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.adapter = Adapteruser

        usermasViewModel.getUsermas.observe(this) { list ->
            list?.let {
                usermas = it as ArrayList<Usermas>
                Adapteruser.updateFoodmas(usermas)
            }
        }
    }

}