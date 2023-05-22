package com.example.kasirmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kasirmas.databinding.ActivityAddUsermasBinding

class AddUsermas : AppCompatActivity() {
    private lateinit var binding : ActivityAddUsermasBinding
    private lateinit var userViewModel: UsermasViewModel
    private var jobdesc = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUsermasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        userViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UsermasViewModel::class.java]

        binding.addUserIbBack.setOnClickListener {
            startActivity(Intent(this, DataUser::class.java ))
            finish()
        }


        val spinner = binding.addUserSpJobdesc

        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_job,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                jobdesc = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.addUserBtnSave.setOnClickListener{
            val name = binding.addUserEtName.text.toString()
            val userName = binding.addUserEtUsername.text.toString()
            val password = binding.addUserEtPassword.text.toString()

            when{

                name.isEmpty() -> alert()
                jobdesc == "Pilih" -> alert()
                userName.isEmpty() -> alert()
                password.isEmpty() -> alert()

                else -> {
                    userViewModel.insertUsermas(Usermas(name = name, job = jobdesc, username = userName , password = password, image = 0))
                    startActivity(Intent(this, DataUser::class.java))
                    finish()
                }
            }
        }
    }


    private fun alert(){
        Toast.makeText(this, "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}