package com.example.assignment_kt.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.assignment_kt.R
import com.example.assignment_kt.fragment.FavoriteUserFragment
import com.example.assignment_kt.fragment.SearchUserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)

        //기본화면
        replaceFragment(SearchUserFragment())

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_search_user -> {
                    replaceFragment(SearchUserFragment())
                }

                R.id.fragment_favorite_user -> {
                    replaceFragment(FavoriteUserFragment())
                }

                else -> false
            }
            true
        }

    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        fragmentTransaction.commit()
    }

}