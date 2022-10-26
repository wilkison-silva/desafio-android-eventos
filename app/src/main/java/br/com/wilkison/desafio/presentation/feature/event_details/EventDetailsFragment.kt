package br.com.wilkison.desafio.presentation.feature.event_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.wilkison.desafio.R
import br.com.wilkison.desafio.databinding.FragmentEventDetailsBinding
import br.com.wilkison.desafio.extensions.tryLoadImage
import br.com.wilkison.desafio.presentation.feature.event_details.states.EventDetailsState
import br.com.wilkison.desafio.presentation.feature.list_events.ListEventsFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsFragment : Fragment(R.layout.fragment_event_details) {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentEventDetailsBinding
    private val viewModel: EventDetailsViewModel by viewModel()

    private val arguments by navArgs<EventDetailsFragmentArgs>()
    private val eventId by lazy {
        arguments.eventId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEventDetailsBinding.bind(view)

        updateEventDetails()
        prepareObservers()
        setupComponents()
    }

    private fun setupComponents() {
        binding.buttonBack.setOnClickListener {
            navController.popBackStack()
        }
        binding.buttonCheckIn.setOnClickListener {
            val direction =
                EventDetailsFragmentDirections.actionEventDetailsFragmentToCheckInFragment(eventId)
            navController.navigate(direction)
        }
    }

    private fun updateEventDetails() {
        viewModel.getEventDetails(eventId = eventId)
    }


    private fun prepareObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.eventDetailsState.collect { eventDetailsState ->
                when (eventDetailsState) {
                    is EventDetailsState.Error -> {
                        binding.progressBarLeft.visibility = View.GONE
                        binding.progressBarRight.visibility = View.GONE
                        binding.textviewErrorMessage.visibility = View.VISIBLE
                    }
                    is EventDetailsState.Loading -> {
                        binding.progressBarLeft.visibility = View.VISIBLE
                        binding.progressBarRight.visibility = View.VISIBLE
                    }
                    is EventDetailsState.Success -> {
                        val eventView = eventDetailsState.eventView
                        binding.apply {
                            imageviewEventImage.tryLoadImage(imageUrl = eventView.image)
                            textviewEventTitle.text = eventView.title
                            textviewEventPrice.text = eventView.price
                            textviewEventDate.text = eventView.date
                            textviewEventDescription.text = eventView.description
                        }
                        binding.progressBarLeft.visibility = View.GONE
                        binding.progressBarRight.visibility = View.GONE
                        binding.layoutSuccess.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

}