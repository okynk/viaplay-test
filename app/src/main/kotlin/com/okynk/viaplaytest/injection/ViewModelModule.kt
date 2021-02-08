package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.feature.screen.sections.SectionsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SectionsViewModel(
            application = androidApplication(),
            scheduler = get(),
            useCase = get()
        )
    }
}
