package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.feature.screen.detail.DetailViewModel
import com.okynk.viaplaytest.feature.screen.sections.SectionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SectionsViewModel(
            application = get(),
            scheduler = get(),
            useCase = get()
        )
    }

    viewModel {
        DetailViewModel(
            application = get(),
            scheduler = get(),
            useCase = get()
        )
    }
}
