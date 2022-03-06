package com.c137.presentation

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity

abstract class BaseActivity<VIEW_BINDING : ViewBinding> : RxAppCompatActivity() {

    private lateinit var binding: VIEW_BINDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding()
        setContentView(binding.root)
    }

    abstract fun viewBinding(): VIEW_BINDING
}