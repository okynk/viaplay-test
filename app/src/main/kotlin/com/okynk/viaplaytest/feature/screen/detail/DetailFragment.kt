package com.okynk.viaplaytest.feature.screen.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.okynk.viaplaytest.R
import com.okynk.viaplaytest.databinding.FragmentDetailBinding
import com.okynk.viaplaytest.feature.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    private val args by navArgs<DetailFragmentArgs>()

    override val viewModel: DetailViewModel by lazy {
        getViewModel()
    }

    override fun getLayoutRes() = R.layout.fragment_detail

    override fun initBinding() {
        viewBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(R.string.detail_title)
        showBackButton(true)

        viewModel.start(args.link)
    }
}