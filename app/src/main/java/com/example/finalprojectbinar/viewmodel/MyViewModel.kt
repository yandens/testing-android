package com.example.finalprojectbinar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.finalprojectbinar.model.LoginRequest
import com.example.finalprojectbinar.model.RegisterRequest
import com.example.finalprojectbinar.repository.MyRepository
import com.example.finalprojectbinar.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel(private val repository: MyRepository) : ViewModel() {

    fun getCourseCategories() = liveData(Dispatchers.IO){
        try {
            emit(Resource.success(data = repository.getCategories()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getAllCourses(categoryId: String?, level: String?, premium: String?, search: String?) = liveData(Dispatchers.IO){
        try {
            emit(Resource.success(data = repository.getCourses(categoryId, level, premium, search)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getDetailByIdCourse(token: String?, coursesId: String) = liveData(Dispatchers.IO){
        try {
            emit(Resource.success(data = repository.getDetailById(token, coursesId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun postLogin(loginRequest: LoginRequest) = liveData(Dispatchers.IO) {
        try {
            val response = repository.postLogin(loginRequest)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun postRegister(registerRequest: RegisterRequest) = liveData(Dispatchers.IO) {
        try {
            val response = repository.postRegister(registerRequest)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun validateJWT(tokenRegister: String?) = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success(data = repository.validateJWT(tokenRegister)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
    fun validateRegister(tokenRegister: String?, otp: String) = liveData(Dispatchers.IO){
        try {
            val response = repository.validateRegister(tokenRegister, otp)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }

    }

    fun getProfileUser(token: String) = liveData(Dispatchers.IO){
        try {
            emit(Resource.success(data = repository.getProfile(token)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}