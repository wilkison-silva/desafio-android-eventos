package br.com.wilkison.desafio.presentation.feature.list_events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.wilkison.desafio.R
import br.com.wilkison.desafio.databinding.FragmentListEventsBinding
import br.com.wilkison.desafio.presentation.feature.event_details.states.EventDetailsState
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

        }
    }

    private fun prepareObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.listEventsState.collect { listEventsState ->
                when (listEventsState) {
                    is ListEventsState.Error -> {
                        binding.textviewErrorMessage.visibility = View.VISIBLE
                        binding.progressBarEventsList.visibility = View.GONE
                    }
                    is ListEventsState.Loading -> {
                        binding.progressBarEventsList.visibility = View.VISIBLE
                        binding.eventsRecyclerview.visibility = View.GONE
                    }
                    is ListEventsState.Success -> {
                        binding.progressBarEventsList.visibility = View.GONE
                        binding.eventsRecyclerview.visibility = View.VISIBLE
                        adapter.updateList(list = listEventsState.shortEventViewList)
                    }
                }
            }
        }
    }

}