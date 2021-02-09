package com.okynk.viaplaytest.util.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import timber.log.Timber

fun Fragment.navigate(directions: NavDirections) {
    val view = this.view ?: return
    try {
        val navController = Navigation.findNavController(view)
        navController.navigate(directions)
    } catch (e: IllegalStateException) {
        // we just don't do transition if we don't have nav controller
        Timber.e("navigate: Can't find navController for $this fragment!")
        return
    }
}