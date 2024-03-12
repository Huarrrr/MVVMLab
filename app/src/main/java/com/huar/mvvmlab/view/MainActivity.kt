package com.huar.mvvmlab.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huar.mvvmlab.R
import com.huar.mvvmlab.di.Injection
import com.huar.mvvmlab.model.ItunesData
import com.huar.mvvmlab.vm.MainViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        Injection.provideViewModelFactory()
    }
    private lateinit var adapter: ItunesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutError: View
    private lateinit var textViewError: TextView
    private lateinit var sortRadio: RadioGroup
    private lateinit var layoutEmpty: View
    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = isDarkTheme(this)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = Color.TRANSPARENT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
    }

    //ui
    private fun setupUI() {
        recyclerView = findViewById(R.id.recyclerView)
        sortRadio = findViewById(R.id.radio)
        layoutError = findViewById(R.id.layoutError)
        layoutEmpty = findViewById(R.id.layoutEmpty)
        progressBar = findViewById(R.id.progressBar)
        textViewError = findViewById(R.id.textViewError)

        sortRadio.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.radio_off -> {
                    adapter.update(viewModel.itunesList.value!!)
                }

                else -> {
                    adapter.update(viewModel.itunesListPrice.value!!)
                }
            }
        }

        adapter = ItunesAdapter(viewModel.itunesList.value!!)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(this, R.drawable.divider_style)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }



    //view model
    private fun setupViewModel() {
        viewModel.itunesList.observe(this, renderItunes)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    //observers
    @SuppressLint("NotifyDataSetChanged")
    private val renderItunes = Observer<MutableList<ItunesData>> {it ->
        Log.v(this.javaClass.simpleName, "data updated $it")
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(this.javaClass.simpleName, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    @SuppressLint("SetTextI18n")
    private val onMessageErrorObserver = Observer<Any> {
        Log.v(this.javaClass.simpleName, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        textViewError.text = "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v(this.javaClass.simpleName, "emptyListObserver $it")
        layoutEmpty.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadItunesList()
    }

    override fun onDestroy() {
        super.onDestroy()
        Injection.destroy()
    }

}