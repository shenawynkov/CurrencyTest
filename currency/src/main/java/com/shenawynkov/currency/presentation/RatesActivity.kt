package com.shenawynkov.currency.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.shenawynkov.currency.R
import com.shenawynkov.currency.domain.model.Rate
import com.shenawynkov.currency.presentation.converter.ConverterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_rates.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RatesActivity : AppCompatActivity(), RatesAdapter.RatesListener {
    private val viewModel: RatesViewModel by viewModels()
    private lateinit var ratesAdapter: RatesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rates)

        //initViews
        ratesAdapter = RatesAdapter(ArrayList(), this)
        rv_rates.apply {
            layoutManager = LinearLayoutManager(this@RatesActivity)
            adapter = ratesAdapter
        }
        //observe
        viewModel.rates.onEach {
            ratesAdapter.setNewList(it)
        }.launchIn(lifecycleScope)

        viewModel.message.onEach {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

            }
        }.launchIn(lifecycleScope)

        viewModel.loading.onEach {
            when (it) {
                true -> progressBar.visibility = View.VISIBLE
                false -> progressBar.visibility = View.GONE
            }

        }.launchIn(lifecycleScope)
    }

    override fun onRateSelected(rate: Rate) {
        val intent = Intent(this, ConverterActivity::class.java)
        intent.putExtra(ConverterActivity.RATE,rate)
        intent.putExtra(ConverterActivity.BASE,"EUR")
        startActivity(intent)
    }
}