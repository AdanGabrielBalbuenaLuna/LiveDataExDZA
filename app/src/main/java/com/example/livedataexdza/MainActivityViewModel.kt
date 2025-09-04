package com.example.livedataexdza

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private lateinit var timer: CountDownTimer
    private val _seconds = MutableLiveData<Int>()
    private var _finished = MutableLiveData<Boolean>()
    val seconds: LiveData<Int> get() = _seconds
    val finished: LiveData<Boolean> get() = _finished

    var timerValue = MutableLiveData<Long>()

    fun startTimer() {
        timer = object : CountDownTimer(timerValue.value!!, 1000) {
            override fun onFinish() {
                _finished.value = true
            }

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000
                _seconds.value = timeLeft.toInt()
            }
        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}