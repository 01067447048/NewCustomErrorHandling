package com.morphine_coder.newcustomerrorhandling.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morphine_coder.newcustomerrorhandling.domain.AuthRepository
import com.morphine_coder.newcustomerrorhandling.domain.Result
import com.morphine_coder.newcustomerrorhandling.domain.UserDataValidator
import kotlinx.coroutines.launch

/**
 * Created by Jaehyeon on 3/18/24.
 */
class UserViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
): ViewModel() {

    fun onRegisterClick(password: String) {
        when(val result = userDataValidator.validatePassword(password = password)) {
            is Result.Error -> {
                when(result.error) {
                    UserDataValidator.PasswordError.TOO_SHORT -> TODO()
                    UserDataValidator.PasswordError.NO_UPPERCASE -> TODO()
                    UserDataValidator.PasswordError.NO_DIGIT -> TODO()
                }
            }
            is Result.Success -> {

            }
        }

        viewModelScope.launch {
            when(val result = repository.register(password = password)) {
                is Result.Error -> {
                    val errorMessage = result.error.asUiText()
                    //Send Event
                }
                is Result.Success -> {
                    result.data
                }
            }
        }
    }
}