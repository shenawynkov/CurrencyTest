package com.shenawynkov.currency.presentation.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.shenawynkov.currency.R
import com.shenawynkov.currency.databinding.ActivityConverterBinding
import com.shenawynkov.currency.domain.model.Rate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_converter.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ConverterActivity : AppCompatActivity() {
    private val viewModel: ConverterViewModel by viewModels()
    private lateinit var binding: ActivityConverterBinding

    companion object {
        const val RATE = "RATE"
        const val BASE = "BASE"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)


        setup()


    }

    fun setup() {
        //binding
        binding = DataBindingUtil.setContentView<ActivityConverterBinding>(
            this,
            R.layout.activity_converter
        )
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        //
        intent.extras?.getParcelable<Rate>(RATE)?.let {
            viewModel.factor = it.value
            binding.tvRate.text = it.key
        }
        binding.tvBase.text = intent.extras?.getString(BASE)

        viewModel.newBase.onEach {
            viewModel.convert()
        }.launchIn(lifecycleScope)

    }
}