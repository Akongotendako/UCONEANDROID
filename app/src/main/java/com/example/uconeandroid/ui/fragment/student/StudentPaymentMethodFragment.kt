package com.example.uconeandroid.ui.fragment.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uconeandroid.R
import com.example.uconeandroid.databinding.FragmentStudentPaymentMethodBinding


class StudentPaymentMethodFragment : Fragment()  {
    private var _binding : FragmentStudentPaymentMethodBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_payment_method, container, false)
    }
}