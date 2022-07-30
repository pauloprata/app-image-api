package com.example.appimagensapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appimagensapi.R
import com.example.appimagensapi.model.ImagesDto
import com.example.appimagensapi.presenter.CatsListContract
import com.example.appimagensapi.presenter.CatsListPresenterImpl

class MainActivity : AppCompatActivity(), CatsListContract.View {

    private lateinit var progressBar: ProgressBar
    private lateinit var rvCatsList: RecyclerView
    private val adapter by lazy { CatsListAdapter() }

    private val presenter = CatsListPresenterImpl.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCatsList = findViewById(R.id.rv_image)
        rvCatsList.setHasFixedSize(true);
        progressBar = findViewById(R.id.progressBar)
        rvCatsList.setLayoutManager(LinearLayoutManager(this));
        rvCatsList.adapter = adapter

        lifecycle.addObserver(presenter)
        presenter.fetchRandomRecipes()




    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }



    override fun displayRecipes(list: List<ImagesDto>) {
        adapter.submitList(list)
    }

    override fun displayLoading(isLoading: Boolean) {
        progressBar.isVisible = isLoading
    }


    override fun showError(message: Int) {
        //txtHello.setText(message)
    }

}