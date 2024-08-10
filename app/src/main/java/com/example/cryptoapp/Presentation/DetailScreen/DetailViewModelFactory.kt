package com.example.cryptoapp.Presentation.DetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModelFactory(private val cryptoId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(cryptoId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}