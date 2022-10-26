package br.com.wilkison.desafio.presentation.feature.event_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.wilkison.desafio.R
import br.com.wilkison.desafio.databinding.FragmentListEventsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsFragment : Fragment(R.layout.fragment_event_details) {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentListEventsBinding
    private val viewModel: EventDetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListEventsBinding.bind(view)

        updateEventDetails()
        prepareObservers()
    }

    private fun updateEventDetails() {

    }


    private fun prepareObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.eventDetailsState.collect {

            }
        }
    }

}