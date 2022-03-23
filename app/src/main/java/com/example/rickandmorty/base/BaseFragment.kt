package com.example.rickandmorty.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding ,V : BaseViewModel> (
    @LayoutRes layoutId : Int
): Fragment(layoutId){

    protected abstract val binding : B
    protected abstract val viewModel : V

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
        setupListeners()
        setupObserve()
        setupRequests()
    }

    open fun setupRequests(){

    }

    open fun setupObserve(){

    }

    open fun setupListeners(){

    }

    open fun setupViews(){

    }

    open fun initialize() {

    }
}