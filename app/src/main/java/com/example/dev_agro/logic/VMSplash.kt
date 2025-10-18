package com.example.dev_agro.logic

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev_agro.utils.KEY_USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val DELAY_TIME = 3000L

@HiltViewModel
class SplashViewModel @Inject constructor(val myPrefs : SharedPreferences): ViewModel(){

    private val _goToMainScreenSharedFlow = MutableSharedFlow<Boolean>()
    val goToMainScreenSharedFlow = _goToMainScreenSharedFlow.asSharedFlow()

    fun navigateAfterDelay(){
        viewModelScope.launch{
            delay(DELAY_TIME)
            _goToMainScreenSharedFlow.emit(
                myPrefs.getLong(KEY_USER_ID, 0L) != 0L
            )
        }
    }
}