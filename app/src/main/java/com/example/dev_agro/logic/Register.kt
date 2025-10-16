package com.example.dev_agro.logic

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev_agro.dto.RegisterDto
import com.example.dev_agro.navigation.Screen
import com.example.dev_agro.network.ApiInterface
/*
import com.example.dev_agro.utils.ASK_SAV
import com.example.dev_agro.utils.CODE_200
import com.example.dev_agro.utils.CODE_303
import com.example.dev_agro.utils.CODE_304
import com.example.dev_agro.utils.CODE_400
import com.example.dev_agro.utils.CODE_503
import com.example.dev_agro.utils.COMPLETE_ALL_THE_FIELDS
import com.example.dev_agro.utils.ERROR_CONNECTION
import com.example.dev_agro.utils.ERROR_REWRITE_PASSWORD
import com.example.dev_agro.utils.Error_LOGIN_ALREADY_USED
import com.example.dev_agro.utils.MESSAGE_WELCOME
import com.example.dev_agro.utils.saveUser
 */
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    //private val api : ApiInterface,
    private val myPrefs: SharedPreferences
): ViewModel()  {

    private val _messageToShowSharedFlow = MutableSharedFlow<String>()
    val messageToShowSharedFloat = _messageToShowSharedFlow.asSharedFlow()

    fun checkFormAndRegister(login : String, password : String, confirmPassword : String){
        /*
        viewModelScope.launch {
            if (login.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()){
                if (password == confirmPassword) {
                    try {
                        val responseRegisterUser = withContext(Dispatchers.IO) {
                            api.registerUser(registerDto = RegisterDto(login, password))
                        }
                        when {
                            responseRegisterUser == null ->
                                _messageToShowSharedFlow.emit(ERROR_CONNECTION)

                            responseRegisterUser.code() == CODE_200 -> {
                                saveUser(responseRegisterUser.body()!!, myPrefs)
                                _messageToShowSharedFlow.emit("$MESSAGE_WELCOME $login")
                            }

                            responseRegisterUser.code() == CODE_303 ->
                                _messageToShowSharedFlow.emit(Error_LOGIN_ALREADY_USED)

                            responseRegisterUser.code() == CODE_304 ->
                                _messageToShowSharedFlow.emit("$ASK_SAV $CODE_304")

                            responseRegisterUser.code() == CODE_400 ->
                                _messageToShowSharedFlow.emit("$ASK_SAV $CODE_400")

                            responseRegisterUser.code() == CODE_503 ->
                                _messageToShowSharedFlow.emit("$ASK_SAV $CODE_503")
                        }
                    }catch (e : Exception){
                        Log.d(Screen.Register.route, e.message ?: "boo")
                    }
                }
                else
                    //_messageToShowSharedFlow.emit(ERROR_REWRITE_PASSWORD)
            }
            else
                _messageToShowSharedFlow.emit(COMPLETE_ALL_THE_FIELDS)
        }
         */
    }

}