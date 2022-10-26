package br.com.wilkison.desafio.presentation.feature.list_events

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.wilkison.desafio.R
import br.com.wilkison.desafio.databinding.FragmentListEventsBinding
import br.com.wilkison.desafio.presentation.feature.list_events.states.ListEventsState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListEventsFragment : Fragment(R.layout.fragment_list_events) {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentListEventsBinding
    private val viewModel: ListEventsViewModel by viewModel()
    private val adapter: ListEventsAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListEventsBinding.bind(view)

        updateEventsList()
        prepareObservers()
        setupRecyclerView()
    }

    private fun updateEventsList() {
        viewModel.getEventsList()
    }

    private fun setupRecyclerView() {
        binding.eventsRecyclerview.adapter = adapter
        adapter.onClickItem = { shortEventView ->
            Log.i("Testando", "Id do item -> ${shortEventView.id}")
        }
    }

    private fun prepareObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.listEventsState.collect { listEventsState ->
                when (listEventsState) {
                    is ListEventsState.Error -> {

                    }
                    is ListEventsState.Loading -> {

                    }
                    is ListEventsState.Success -> {
                        adapter.updateList(list = listEventsState.shortEventViewList)
                    }
                }
            }
        }
    }

}