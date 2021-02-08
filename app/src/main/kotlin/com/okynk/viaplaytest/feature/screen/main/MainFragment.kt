package com.okynk.viaplaytest.feature.screen.main

import android.os.Bundle
import android.view.View
import com.okynk.viaplaytest.R
import com.okynk.viaplaytest.databinding.FragmentMainBinding
import com.okynk.viaplaytest.feature.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {
    override fun getLayoutRes() = R.layout.fragment_main

    override val viewModel: MainViewModel by lazy {
        getViewModel()
    }

    override fun initBinding() {
        viewBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.start()
    }
}