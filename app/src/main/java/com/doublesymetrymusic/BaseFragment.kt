package com.doublesymetrymusic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.doublesymetrymusic.extensions.showToast
import com.doublesymetrymusic.presentation.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel>(
  private val layoutId: Int
) : Fragment() {

  protected lateinit var binding: BINDING
  val viewModel: VIEW_MODEL by lazy { getViewModel(clazz = viewModelClass()) }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    binding.lifecycleOwner = viewLifecycleOwner

    setupBinding()

    return binding.root
  }

  abstract fun setupBinding()

  @Suppress("UNCHECKED_CAST")
  private fun viewModelClass(): KClass<VIEW_MODEL> {
    // https://stackoverflow.com/a/1901275/719212
    return ((javaClass.genericSuperclass as ParameterizedType)
      .actualTypeArguments[1] as Class<VIEW_MODEL>).kotlin
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    lifecycle.addObserver(viewModel)

    subscribeToNoInternetLiveData()
    subscribeToNoInternetLiveData()
    subscribeToShowMessageLiveData()
  }

  private fun subscribeToNoInternetLiveData() {
    viewModel.internetErrorLiveData.observe(viewLifecycleOwner, {
      context?.showToast(R.string.error_internet)
    })
  }

  private fun subscribeToGenericErrorLiveDataLiveData() {
    viewModel.genericErrorLiveData.observe(viewLifecycleOwner, {
      context?.showToast(R.string.error_generic)
    })
  }

  private fun subscribeToShowMessageLiveData() {
    viewModel.showMessageResId.observe(viewLifecycleOwner, { stringId -> stringId?.let { context?.showToast(it) } })
  }
}
