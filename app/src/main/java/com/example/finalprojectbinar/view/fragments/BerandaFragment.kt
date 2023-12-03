package com.example.finalprojectbinar.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectbinar.R
import com.example.finalprojectbinar.databinding.FragmentBerandaBinding
import com.example.finalprojectbinar.model.CoursesResponses
import com.example.finalprojectbinar.model.ListCategoriesResponse
import com.example.finalprojectbinar.util.Status
import com.example.finalprojectbinar.view.adapters.CategoryAdapter
import com.example.finalprojectbinar.view.adapters.CourseAdapter
import com.example.finalprojectbinar.viewmodel.MyViewModel
import org.koin.android.ext.android.inject
import kotlin.math.log

class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)


        fetchCategoryCoroutines()
        fetchCourseCouroutines()

        return binding.root
    }


    private fun fetchCategoryCoroutines() {
        viewModel.getCourseCategories().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    showCategories(it.data!!)
                    binding.progressBarCategory.visibility = View.GONE
                }

                Status.ERROR -> {
                    Log.d("Error", "Error Occured!")
                }

                Status.LOADING -> {
                    binding.progressBarCategory.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun fetchCourseCouroutines() {
        viewModel.getAllCourses().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("DATATEST", it.data.toString())
                    showCourses(it.data!!)
                    binding.progressBarCourse.visibility = View.GONE
                }

                Status.ERROR -> {
                    Log.d("Error", "Error Occured!")
                }

                Status.LOADING -> {
                    binding.progressBarCourse.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showCategories(data: ListCategoriesResponse?){
        val adapter = CategoryAdapter()
        adapter.submitCategoryMenuResponse(data?.data ?: emptyList())
        binding.gridviewKategori.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.gridviewKategori.adapter = adapter
    }

    private fun showCourses(data: CoursesResponses?){
        val adapter = CourseAdapter(null)
        adapter.submitCoursesResponse(data?.data ?: emptyList())
        binding.rvCourses.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCourses.adapter = adapter
    }
}