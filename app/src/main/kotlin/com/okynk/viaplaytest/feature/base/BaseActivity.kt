package com.okynk.viaplaytest.feature.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.okynk.viaplaytest.R
import com.okynk.viaplaytest.util.NO_RES

abstract class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    fun setContentViewWithNavGraph(
        @LayoutRes layoutRes: Int = R.layout.activity_base,
        @NavigationRes navGraph: Int,
        bundle: Bundle? = null,
        @IdRes startDestinationFragment: Int = NO_RES
    ) {
        setContentView(layoutRes)
        val graph = findNavController(R.id.nav_host_fragment).navInflater.inflate(navGraph)
        if (startDestinationFragment != NO_RES) {
            graph.startDestination = startDestinationFragment
        }
        findNavController(R.id.nav_host_fragment).setGraph(graph, bundle)
    }
}
