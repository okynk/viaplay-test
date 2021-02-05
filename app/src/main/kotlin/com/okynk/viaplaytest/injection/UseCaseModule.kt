package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.usecase.UseCase
import com.okynk.viaplaytest.usecase.UseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<UseCase> {
        UseCaseImpl(
                repository = get(),
                scheduler = get()
        )
    }
}
