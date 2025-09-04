package com.example.livedataexdza

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        val inputNumber: EditText = findViewById<EditText>(R.id.editTextView)
        val startButton: Button = findViewById<Button>(R.id.startButton)
        val stopButton: Button = findViewById<Button>(R.id.stopButton)


        //val viewModel = MainActivityViewModel()
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.seconds.observe(this, Observer {
            numberTxt.text = it.toString()
        })

        viewModel.finished.observe(this, Observer{
            if(it) {
                Toast.makeText(this, "Finished", Toast.LENGTH_LONG).show()
            }
        })

        startButton.setOnClickListener {
            if (inputNumber.text.isEmpty() || inputNumber.text.length !in 4..5) {
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_LONG).show()
            } else {
                viewModel.timerValue.value = inputNumber.text.toString().toLong()
                viewModel.startTimer()
            }
        }

        stopButton.setOnClickListener {
            numberTxt.text = "0"
            viewModel.stopTimer()
        }

    }
}