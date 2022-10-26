package br.com.wilkison.desafio.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.wilkison.desafio.R
import br.com.wilkison.desafio.presentation.feature.list_events.ListEventsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val viewModel: ListEventsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getEventsList()
    }
}