package com.example.dev_agro.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev_agro.dto.ProductDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    //private val api : ApiInterface,
): ViewModel() {

    private val _messageToShowSharedFlow = MutableSharedFlow<String>()
    val messageToShowSharedFloat = _messageToShowSharedFlow.asSharedFlow()

    private var articlesList : List<ProductDto> = listOf()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private fun addPhoto() {

    }

    fun refresh(listProducts: List<ProductDto>) {

    }
}