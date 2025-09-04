package com.example.livedataexdza

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val numberTxt:TextView = findViewById<TextView>(R.id.counter)


        //val viewModel = MainActivityViewModel()
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.startTimer()
        viewModel.seconds.observe(this, Observer {
            numberTxt.text = it.toString()
        })

        viewModel.finished.observe(this, Observer{
            if(it) {
                Toast.makeText(this, "Finished", Toast.LENGTH_LONG).show()
            }
        })
    }
}