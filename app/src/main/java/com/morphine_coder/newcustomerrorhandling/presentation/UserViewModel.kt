package com.morphine_coder.newcustomerrorhandling.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morphine_coder.newcustomerrorhandling.domain.AuthRepository
import com.morphine_coder.newcustomerrorhandling.domain.DataError
import com.morphine_coder.newcustomerrorhandling.domain.Result
import com.morphine_coder.newcustomerrorhandling.domain.RootError
import com.morphine_coder.newcustomerrorhandling.domain.UserDataValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

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
                    UserDataValidator.PasswordError.TOO_SHORT -> {
                        val errorMessage = result.error.asUiText()
                    }
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

        doSomeThingWithErrorHandler {

        }
    }
}

inline fun ViewModel.doSomeThingWithErrorHandler(
    crossinline block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch {
    withContext(Dispatchers.IO) {
        block.invoke(this)
    }

}

suspend fun DataError.errorHandler() {
    withContext(Dispatchers.Main) {
        when(this@errorHandler) {
            DataError.Local.DISK_FULL -> TODO()
            DataError.Network.REQUEST_TIMEOUT -> TODO()
            DataError.Network.TOO_MANY_REQUESTS -> TODO()
            DataError.Network.NO_INTERNET -> TODO()
            DataError.Network.PAYLOAD_TOO_LARGE -> TODO()
            DataError.Network.SERVER_ERROR -> TODO()
            DataError.Network.SERIALIZATION -> TODO()
            DataError.Network.UNKNOWN -> TODO()
        }
    }
}


//inline fun <D, E: RootError> ViewModel.doSomeThingWithErrorHandler(
//    crossinline block: suspend (Result<D, E>) -> Unit
//) = viewModelScope.launch {
//
//}

//inline fun ViewModel.onIO(
//    crossinline exceptionHandler: (Exception) -> Unit = {},
//    crossinline block: suspend CoroutineScope.() -> Unit
//) = viewModelScope.launch(Dispatchers.IO + throwableExceptionHandler) {
//    try{
//        block.invoke(this)
//    }catch (cancelException: CancellationException){
//        Logg.w("coroutine cancel")
//    }catch (exception: Exception){
//        exceptionHandler.invoke(exception)
//    }
//}
//inline fun ViewModel.onThrowableIO(
//    crossinline block: suspend CoroutineScope.() -> Unit,
//    crossinline launchBlock: () -> Unit
//) = viewModelScope.launch(throwableExceptionHandler) {
//    val result = withContext(Dispatchers.IO) { block.invoke(this) }
//    if(result !is Error) {
//        launchBlock.invoke()
//    }
//}