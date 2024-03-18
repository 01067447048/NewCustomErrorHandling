package com.morphine_coder.newcustomerrorhandling.domain

/**
 * Created by Jaehyeon on 3/18/24.
 */
interface AuthRepository {

    suspend fun register(password: String): Result<User, DataError.Network>
}

data class User(
    val name: String,
    val token: String,
    val email: String
)