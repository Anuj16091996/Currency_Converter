package com.example.application_convercy_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.application_convercy_converter.db.AppDatabase
import com.example.application_convercy_converter.recycleView.FavoriteAdapter

class MainActivityFavroite : AppCompatActivity() {
    private lateinit var dataBase: AppDatabase
    private var favoriteAdapter = FavoriteAdapter()
    private lateinit var recyclerView: RecyclerView
    private var dataBoolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_favroite)
        dataBase = AppDatabase.getDatabaseInstance(this)
        recyclerView = findViewById<RecyclerView>(R.id.favroite_recycle)
        AppDatabase.databaseWriteExecutor.execute {
            val customersDeatils = dataBase.CurrencyDAO().getAllUser()
            recyclerView.adapter = favoriteAdapter
            favoriteAdapter.changeData(customersDeatils)
        }
        recyclerView.setHasFixedSize(true)
    }

    override fun onPause() {
        super.onPause()
        dataBoolean = false
    }

    override fun onResume() {
        super.onResume()
        if (!dataBoolean) {
            finish();
            startActivity(getIntent());
        }
    }

    override fun onStop() {
        super.onStop()
    }
}