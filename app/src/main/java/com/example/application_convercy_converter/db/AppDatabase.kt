package com.example.application_convercy_converter.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.application_convercy_converter.db.dao.CurrencyDAO
import com.reza.roomapplication.db.entities.CurrencyBook
import java.util.concurrent.Executors

//TODO: Add Entities and change version if you modified the database
@Database(entities = [CurrencyBook::class], version = 17)
abstract class AppDatabase : RoomDatabase() {

    //TODO: Add DAOs here
    //DAO (Database Access Objects)
    abstract fun CurrencyDAO(): CurrencyDAO

    companion object {
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        private var INSTANCE: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() //Modified/Changed this will destroy the DB and recreate it.
                    .build()

                INSTANCE = instance

                instance //returning
            }
        }
    }

}