package com.morphine_coder.newcustomerrorhandling.data

import com.morphine_coder.newcustomerrorhandling.domain.AuthRepository
import com.morphine_coder.newcustomerrorhandling.domain.DataError
import com.morphine_coder.newcustomerrorhandling.domain.Result
import com.morphine_coder.newcustomerrorhandling.domain.User
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Jaehyeon on 3/18/24.
 */
class AuthRepositoryImpl: AuthRepository {
    override suspend fun register(password: String): Result<User, DataError.Network> {
        return try {
            val user = User("dummy", "dummy", "dummy")
            Result.Success(user)
        } catch (e: HttpException) {
            when(e.code()) {
                408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        } catch (e: IOException) {
            Result.Error(DataError.Network.NO_INTERNET)
        } catch (t: Throwable) {
            Result.Error(DataError.Network.UNKNOWN)
        }
    }
}