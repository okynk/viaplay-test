package com.okynk.viaplaytest.feature.screen.sections

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.okynk.viaplaytest.R
import com.okynk.viaplaytest.databinding.FragmentSectionsBinding
import com.okynk.viaplaytest.feature.base.BaseFragment
import com.okynk.viaplaytest.feature.screen.sections.epoxy.SectionsEpoxyController
import com.okynk.viaplaytest.util.extensions.navigate
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SectionsFragment : BaseFragment<SectionsViewModel, FragmentSectionsBinding>() {

    private val controller: SectionsEpoxyController by lazy {
        SectionsEpoxyController(onItemClicked = {
            navigate(SectionsFragmentDirections.openDetail(it))
        })
    }

    override val viewModel: SectionsViewModel by lazy {
        getViewModel()
    }

    override fun getLayoutRes() = R.layout.fragment_sections

    override fun initBinding() {
        viewBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(R.string.sections_title)
        showBackButton(false)
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