package com.example.kasirmas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsermasViewModel (application: Application): AndroidViewModel(application) {
    val getUsermas: LiveData<List<Usermas>>
    val repository : KasirRepo

    init {
        val dao = KasirDb.getDatabase(application).getDaoKsr()
        repository = KasirRepo(dao)
        getUsermas = repository.usermas
    }

    fun insertUsermas(usermas: Usermas) = viewModelScope.launch(Dispatchers.IO){
        repository.insertUsermas(usermas)
    }

    fun deleteUsermas(usermas: Usermas) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteUsermas(usermas)
    }

    fun updateUsermas(usermas: Usermas) = viewModelScope.launch(Dispatchers.IO){
        repository.updateUsermas(usermas)
    }

}