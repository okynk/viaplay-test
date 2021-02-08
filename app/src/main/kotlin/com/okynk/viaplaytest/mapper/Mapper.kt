package com.okynk.viaplaytest.mapper

interface Mapper<in FROM, TO> {
    fun map(from: FROM): TO
}