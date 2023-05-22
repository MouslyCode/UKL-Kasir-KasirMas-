package com.example.kasirmas

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface KasirDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  insertFoodmas(foodmas: Foodmas)

    @Delete
    suspend fun  deleteFoodmas(foodmas: Foodmas)

    @Update
    suspend fun  updateFoodmas(foodmas: Foodmas)

    @Query("SELECT * FROM foodmas ORDER BY name ASC")
    fun getFoodmas(): LiveData<List<Foodmas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  insertDrinkmas(drinkmas: Drinkmas)

    @Delete
    suspend fun  deleteDrinkmas(drinkmas: Drinkmas)

    @Update
    suspend fun updateDrinkmas(drinkmas: Drinkmas)

    @Query("SELECT * FROM drinkmas ORDER BY name ASC")
    fun getDrinkmas(): LiveData<List<Drinkmas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsermas(usermas: Usermas)

    @Delete
    suspend fun deleteUsermas(usermas: Usermas)

    @Update
    suspend fun updateUsermas(usermas: Usermas)

    @Query("SELECT * FROM usermas")
    fun getUsermas(): LiveData<List<Usermas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMejamas(mejamas: Mejamas)

    @Delete
    suspend fun deleteMejamas(mejamas: Mejamas)

    @Update
    suspend fun updateMejamas(mejamas: Mejamas)

    @Query("SELECT * FROM mejamas")
    fun getMejamas(): LiveData<List<Mejamas>>

}