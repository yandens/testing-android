package com.example.finalprojectbinar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.finalprojectbinar.repository.MyRepository
import com.example.finalprojectbinar.util.Resource
import kotlinx.coroutines.Dispatchers


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

    fun getDetailByIdCourse(coursesId: String) = liveData(Dispatchers.IO){
        try {
            emit(Resource.success(data = repository.getDetailById(coursesId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}