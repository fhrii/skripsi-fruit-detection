package com.fahriaf.fruitdetection.ui.detail

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fahriaf.fruitdetection.core.domain.usecase.FruitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val fruitUseCase: FruitUseCase) : ViewModel() {
    fun getFruitDetection(image: Bitmap) = fruitUseCase.getFruitDetection(image).asLiveData()
}