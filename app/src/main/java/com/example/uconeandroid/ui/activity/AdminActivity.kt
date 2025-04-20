package com.example.uconeandroid.ui.activity

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.uconeandroid.R
import com.example.uconeandroid.ui.fragment.admin.AccountFragment
import com.example.uconeandroid.ui.fragment.admin.HomeFragment
import com.example.uconeandroid.ui.fragment.admin.AdminShopFragment
import com.example.uconeandroid.ui.fragment.admin.StudentFragment

class AdminActivity : AppCompatActivity() {
    private var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin)

        initializeFragment(fragment = HomeFragment())
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navItems = listOf(
            findViewById(R.id.adminNavHome),
            findViewById(R.id.adminNavShop),
            findViewById(R.id.adminNavStudent),
            findViewById<ImageButton>(R.id.adminNavAccount),
        )

        navItems.forEach { item ->
            item.setOnClickListener {
                when(item.id) {
                    R.id.adminNavHome -> initializeFragment(HomeFragment())
                    R.id.adminNavShop -> initializeFragment(AdminShopFragment())
                    R.id.adminNavStudent -> initializeFragment(StudentFragment())
                    R.id.adminNavAccount-> initializeFragment(AccountFragment())
                }
                updateNavigation(item.id)
            }
        }
    }

    private fun initializeFragment(fragment: Fragment) {
        if (currentFragment?.javaClass == fragment.javaClass) return
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
        currentFragment = fragment
    }

    private fun updateNavigation(selectedItem: Int) {
        listOf(
            R.id.adminNavHome,
            R.id.adminNavShop,
            R.id.adminNavStudent,
            R.id.adminNavAccount
        ).forEach {
            val item = findViewById<ImageButton>(it)
            val isSelected = it == selectedItem

            item.setColorFilter(
                ContextCompat.getColor(this,
                    if (isSelected) R.color.surfaceVariant else R.color.onSurfaceContainer)
            )
        }
    }
}