package com.briggin.average.property.price.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.briggin.average.property.price.R
import com.briggin.average.property.price.presentation.PropertyViewModel
import kotlinx.android.synthetic.main.fragment_property.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class PropertyFragment : Fragment(R.layout.fragment_property) {

    private val viewModel: PropertyViewModel by viewModel()

    private val scope get() = viewLifecycleOwner.lifecycleScope

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        reloadButton.setOnClickListener { requestAveragePropertyPrice() }
        if (savedInstanceState == null) requestAveragePropertyPrice()
    }

    private fun requestAveragePropertyPrice() {
        scope.launch(Dispatchers.Default) { viewModel.getPropertyAverage() }
    }

    private fun observeViewModel() {
        viewModel.propertyAverage
            .onEach { propertyAverageValue.text = it }
            .flowOn(Dispatchers.Main)
            .launchIn(scope)

        viewModel.error
            .onEach { resId -> errorText.text = resId?.let { resources.getString(it) } ?: "" }
            .flowOn(Dispatchers.Main)
            .launchIn(scope)
    }

    companion object {
        fun newInstance() = PropertyFragment()
    }
}
