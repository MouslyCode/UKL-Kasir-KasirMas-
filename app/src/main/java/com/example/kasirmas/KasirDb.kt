package com.example.kasirmas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Foodmas::class, Drinkmas::class, Usermas::class, Mejamas::class],
    version = 2,
    exportSchema = false
)
abstract class KasirDb : RoomDatabase(){
    abstract fun getDaoKsr(): KasirDao

    companion object{
        @Volatile
        private var INSTANCE: KasirDb? = null

        fun getDatabase(context: Context): KasirDb {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    KasirDb::class.java,
                    "kasir"
                ).fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}