package com.edu.appfeature.features.viewbinding

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edu.appfeature.R
import com.edu.appfeature.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {

    // create the object binding
    private lateinit var binding: ActivityViewBindingBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_binding)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back button
        binding.btnBack.setOnClickListener {
            // action
        }

        // text view
        binding.tvWelcome.text = "Welcome \nto binding page"
        binding.tvDescription.text = "When view binding is enabled in a module, a binding class is generated for each XML layout file in that module. A binding class instance has direct references to all views in the related layout that have an ID"

        // ok button
        binding.btnOk.setOnClickListener {
            // action
        }
    }
}