package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.feature.screen.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(
            application = androidApplication(),
            scheduler = get(),
            useCase = get()
        )
    }
}
