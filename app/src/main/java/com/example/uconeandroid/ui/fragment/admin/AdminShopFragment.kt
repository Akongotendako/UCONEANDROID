package com.example.uconeandroid.ui.fragment.admin


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.uconeandroid.R
import com.example.uconeandroid.databinding.FragmentAdminShopBinding

class AdminShopFragment: Fragment() {

    private var selectedImageUri: Uri? = null
    private var _binding: FragmentAdminShopBinding?  =null
    private val binding get() = _binding!!

    private val imagePickLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {uri: Uri? ->
        uri?.let {
            selectedImageUri = it

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminShopBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToAddItemScreen(view)
    }

    private fun navigateToAddItemScreen (view: View) {
        view.findViewById<Button>(R.id.adminShopAddItem).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AdminShopAddItemFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}