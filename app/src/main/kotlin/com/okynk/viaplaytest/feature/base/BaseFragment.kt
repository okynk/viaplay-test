package com.okynk.viaplaytest.feature.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.okynk.viaplaytest.R
import com.okynk.viaplaytest.model.MessageDialogEntity
import com.okynk.viaplaytest.model.ScreenEntity
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*

abstract class BaseFragment<VM : BaseViewModel, BINDING : ViewDataBinding> : Fragment() {
    protected lateinit var viewBinding: BINDING
    protected abstract fun getLayoutRes(): Int
    protected abstract val viewModel: VM

    abstract fun initBinding()

    protected val handleOnBackPressed = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            viewModel.onBackPressed()
        }
    }

    protected lateinit var messageDialog: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(handleOnBackPressed)
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
                containerMaster.container_content,
                true
            )
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

    private fun initObservers() {
        viewModel.closeActivity.observe(viewLifecycleOwner) {
            requireActivity().finish()
        }

        viewModel.showLoadingOverlay.observe(viewLifecycleOwner) {
            container_loading.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.showMessageDialog.observe(viewLifecycleOwner) {
            showMessageDialog(it)
        }

        viewModel.goBack.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }

        viewModel.startActivity.observe(viewLifecycleOwner) {
            redirectPage(it)
        }
    }

    private fun redirectPage(screen: ScreenEntity) {
        startActivity(
            when (screen) {
                ScreenEntity.SectionList -> TODO()
                ScreenEntity.SectionDetail -> TODO()
            }
        )
    }
}
