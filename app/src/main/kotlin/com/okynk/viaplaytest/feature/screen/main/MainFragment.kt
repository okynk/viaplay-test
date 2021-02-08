package com.okynk.viaplaytest.feature.screen.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.okynk.viaplaytest.R
import com.okynk.viaplaytest.databinding.FragmentMainBinding
import com.okynk.viaplaytest.feature.base.BaseFragment
import com.okynk.viaplaytest.feature.screen.main.epoxy.MainEpoxyController
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    private val controller: MainEpoxyController by lazy {
        MainEpoxyController()
    }

    override val viewModel: MainViewModel by lazy {
        getViewModel()
    }

    override fun getLayoutRes() = R.layout.fragment_main

    override fun initBinding() {
        viewBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(R.string.sectionlist_title)
        initObservers()
        initList()
        viewModel.start()
    }

    private fun initObservers() {
        viewModel.listData.observe(viewLifecycleOwner) {
            controller.setData(it)
        }
    }

    private fun initList() {
        with(viewBinding.rvMain) {
            layoutManager = LinearLayoutManager(requireContext())
            setController(controller)
        }
    }
}