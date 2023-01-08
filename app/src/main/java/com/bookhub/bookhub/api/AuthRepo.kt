package com.bookhub.bookhub.api

import com.bookhub.bookhub.models.LoginUser
import com.bookhub.bookhub.models.RegisterUser
import com.bookhub.bookhub.models.Response
import com.bookhub.bookhub.models.User
import com.bookhub.bookhub.utils.LocalStorageUtil
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.json.JSONObject

class AuthRepo(private val authApi: AuthApi,private val localStorageUtil: LocalStorageUtil) {

    suspend fun login(email : String, password : String) : Response<User> {
        return try{
            val result = authApi.login(LoginUser(
                email = email,
                password = password
            ))

            localStorageUtil.setToken(result.token)
            Response.Success(result.user)
        } catch(e :Exception){
            Response.Error(e.localizedMessage ?: "")
        }
    }

    suspend fun register(firstName : String, lastName : String, email : String, password : String) : Response<User>{
        return try{
            val result = authApi.register(RegisterUser(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password,
                passwordConfirm = password
            ))
            Response.Success(result)
        } catch(e :Exception){
            Response.Error(e.localizedMessage ?: "")
        }
    }

    suspend fun logout() : Response<JSONObject>{
        return try{
            Response.Success(authApi.logout())
        }
        catch(e : Exception){
            Response.Error(e.localizedMessage ?: "")
        }
    }

}