package com.example.dev_agro.logic

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev_agro.R
import com.example.dev_agro.network.ApiInterface
/*
import com.example.dev_agro.utils.ASK_SAV
import com.example.dev_agro.utils.CODE_200
import com.example.dev_agro.utils.CODE_304
import com.example.dev_agro.utils.CODE_400
import com.example.dev_agro.utils.CODE_401
import com.example.dev_agro.utils.CODE_503
 */
import com.example.dev_agro.utils.COMPLETE_ALL_THE_FIELDS
/*
import com.example.dev_agro.utils.ERROR_CONNECTION
import com.example.dev_agro.utils.ERROR_UNKNOWN_USER
import com.example.dev_agro.utils.Error_LOGIN_ALREADY_USED
*/
import com.example.dev_agro.utils.LOGIN
import com.example.dev_agro.utils.MESSAGE_WELCOME
import com.example.dev_agro.utils.saveUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    //private val api : ApiInterface,
    private val myPrefs: SharedPreferences
): ViewModel()  {

    private val _messageToShowSharedFlow = MutableSharedFlow<String>()
    val messageToShowSharedFloat = _messageToShowSharedFlow.asSharedFlow()


    fun checkFormAndLogin(login: String, password : String){
        viewModelScope.launch {
            if (login.isBlank() || password.isBlank())
                _messageToShowSharedFlow.emit(COMPLETE_ALL_THE_FIELDS)
            else{
                try {
                    val responseLoginUser = withContext(Dispatchers.IO) {
                        //api.loginUser(userName = login, password = password)
                    }
                    /*
                    when {
                        responseLoginUser == null ->
                            _messageToShowSharedFlow.emit(ERROR_CONNECTION)

                        responseLoginUser.code() == CODE_200 -> {
                            saveUser(responseLoginUser.body()!!, myPrefs)
                            _messageToShowSharedFlow.emit("$MESSAGE_WELCOME $login")
                        }

                        responseLoginUser.code() == CODE_304 ->
                            _messageToShowSharedFlow.emit("$ASK_SAV $CODE_304")

                        responseLoginUser.code() == CODE_400 ->
                            _messageToShowSharedFlow.emit("$ASK_SAV $CODE_400")

                        responseLoginUser.code() == CODE_401 ->
                            _messageToShowSharedFlow.emit(ERROR_UNKNOWN_USER)

                        responseLoginUser.code() == CODE_503 ->
                            _messageToShowSharedFlow.emit("$ASK_SAV $CODE_503")
                    }
                     */
                }
                catch (e : Exception){
                    Log.d(LOGIN, e.message ?: "boo")
                }
            }
        }
    }
}