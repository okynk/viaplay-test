package com.okynk.viaplaytest

import com.okynk.viaplaytest.injection.mapperModule
import com.okynk.viaplaytest.injection.testRxModule
import com.okynk.viaplaytest.injection.utilModule
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

abstract class BaseTest : KoinTest {

    protected val testScheduler: SchedulerProvider by inject()

    @Before
    open fun setUp() {
        startKoin {
            modules(testRxModule + mapperModule + utilModule)
        }
    }

    @After
    open fun tearDown() {
        stopKoin()
    }
}
