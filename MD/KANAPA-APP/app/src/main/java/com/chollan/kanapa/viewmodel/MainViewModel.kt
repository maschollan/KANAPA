package com.chollan.kanapa.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.chollan.kanapa.api.ApiConfig
import com.chollan.kanapa.response.PredictResponse
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.chollan.kanapa.request.LoginRequest
import com.chollan.kanapa.request.RegisterRequest
import com.chollan.kanapa.response.LoginResponse
import com.chollan.kanapa.response.LoginResult
import com.chollan.kanapa.response.PostResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        const val TAG = "KANAPA"
    }

    private val _postPredict = mutableStateOf(PredictResponse(0f, ""))
    val postPredict: State<PredictResponse> = _postPredict

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _postRegister = mutableStateOf(PostResponse(false, ""))
    val postRegister: State<PostResponse> = _postRegister

    private val _postLogin = mutableStateOf(
        LoginResponse(
            LoginResult("", "", ""), false, ""
        )
    )

    val postLogin: State<LoginResponse> = _postLogin

    init {
        _isLoading.value = false
    }

    fun predict(file: MultipartBody.Part) {
        _isLoading.value = true
        _postPredict.value = PredictResponse(0f, "")
        val client = ApiConfig.getPredictService().predict(file)
        client.enqueue(object : Callback<PredictResponse> {
            override fun onResponse(
                call: Call<PredictResponse>, response: Response<PredictResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _postPredict.value = response.body()!!
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getAuthService().postLogin(LoginRequest(email, password))
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _postLogin.value = response.body()!!
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun register(name: String, email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getAuthService().postRegister(RegisterRequest(name, email, password))
        client.enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _postRegister.value = response.body()!!
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun reset() {
        _postRegister.value = PostResponse(false, "")
        _postLogin.value = LoginResponse(
            LoginResult("", "", ""), false, ""
        )
    }
}