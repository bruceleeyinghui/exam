package com.example.exam.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class MyViewModel : ViewModel() {
    val mAmount = MutableLiveData<String>()
    val mTime = MutableLiveData<String>()

    val sumList: MutableList<Double> = mutableListOf()

}