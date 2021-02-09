package com.okynk.viaplaytest.feature.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.okynk.viaplaytest.R
import com.okynk.viaplaytest.model.MessageDialogEntity

abstract class BaseFragment<VM : BaseViewModel, BINDING : ViewDataBinding> : Fragment() {
    protected lateinit var viewBinding: BINDING
    protected abstract fun getLayoutRes(): Int
    protected abstract val viewModel: VM

    abstract fun initBinding()

    protected lateinit var messageDialog: MaterialDialog

    private lateinit var containerLoading: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messageDialog = MaterialDialog(requireContext())
            .title(R.string.general_title_error)
            .message(R.string.general_label_error)
            .positiveButton(R.string.general_button_ok)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val containerMaster = inflater.inflate(R.layout.fragment_base, null)
        viewBinding =
            DataBindingUtil.inflate(
                inflater,
                getLayoutRes(),
                containerMaster.findViewById(R.id.container_content),
                true
            )
        containerLoading = containerMaster.findViewById(R.id.container_loading)
        viewBinding.lifecycleOwner = this
        initBinding()
        return containerMaster
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    protected fun showMessageDialog(entity: MessageDialogEntity) {
        messageDialog.title(text = entity.title, res = entity.titleRes)
        messageDialog.message(text = entity.message, res = entity.messageRes)
        messageDialog.show()
    }

    protected fun setToolbarTitle(@StringRes titleRes: Int) {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = requireContext().getString(titleRes)
        }
    }

    protected fun showBackButton(show: Boolean) {
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(show)
            setDisplayShowHomeEnabled(show)
        }
    }

    private fun initObservers() {
        viewModel.closeActivity.observe(viewLifecycleOwner) {
            requireActivity().finish()
        }

        viewModel.showLoadingOverlay.observe(viewLifecycleOwner) {
            if (::containerLoading.isInitialized) {
                containerLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        viewModel.showMessageDialog.observe(viewLifecycleOwner) {
            showMessageDialog(it)
        }
    }
}
