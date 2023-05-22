package com.example.kasirmas

import androidx.lifecycle.LiveData

class KasirRepo (private val daoKsr: KasirDao){
    val foodmass : LiveData<List<Foodmas>> = daoKsr.getFoodmas()
    val drinkmass : LiveData<List<Drinkmas>> = daoKsr.getDrinkmas()
    val usermas: LiveData<List<Usermas>> = daoKsr.getUsermas()
    val table : LiveData<List<Mejamas>> = daoKsr.getMejamas()


    suspend fun insertFoodmas(foodmas: Foodmas){
        daoKsr.insertFoodmas(foodmas)
    }

    suspend fun deleteFoodmas(foodmas: Foodmas){
        daoKsr.deleteFoodmas(foodmas)
    }

    suspend fun updateFoodmas(foodmas: Foodmas){
        daoKsr.updateFoodmas(foodmas)
    }

    suspend fun insertDrinkmas(drinkmas: Drinkmas){
        daoKsr.insertDrinkmas(drinkmas)
    }

    suspend fun deleteDrinkmas(drinkmas: Drinkmas){
        daoKsr.deleteDrinkmas(drinkmas)
    }

    suspend fun updateDrinkmas(drinkmas: Drinkmas){
        daoKsr.updateDrinkmas(drinkmas)
    }
    suspend fun insertUsermas(usermas: Usermas){
        daoKsr.insertUsermas(usermas)
    }

    suspend fun deleteUsermas(usermas: Usermas){
        daoKsr.deleteUsermas(usermas)
    }

    suspend fun updateUsermas(usermas: Usermas){
        daoKsr.updateUsermas(usermas)
    }

    suspend fun insertMejamas(mejamas: Mejamas){
        daoKsr.insertMejamas(mejamas)
    }

    suspend fun deleteMejamas(mejamas: Mejamas){
        daoKsr.deleteMejamas(mejamas)
    }

    suspend fun updateMejamas(mejamas: Mejamas){
        daoKsr.updateMejamas(mejamas)
    }



}