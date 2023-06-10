package com.chollan.kanapa.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chollan.kanapa.response.ApiConfig
import com.chollan.kanapa.response.PredictResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    init {
        _isLoading.value = false
    }

    fun predict(file: MultipartBody.Part) {
        _isLoading.value = true
        _postPredict.value = PredictResponse(0f, "")
        val client = ApiConfig.getPredictService().predict(file)
        client.enqueue(object : Callback<PredictResponse> {
            override fun onResponse(
                call: Call<PredictResponse>,
                response: Response<PredictResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful) {
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
}