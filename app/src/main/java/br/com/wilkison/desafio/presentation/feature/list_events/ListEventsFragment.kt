package br.com.wilkison.desafio.presentation.feature.list_events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.wilkison.desafio.R
import br.com.wilkison.desafio.databinding.FragmentListEventsBinding

class ListEventsFragment : Fragment(R.layout.fragment_list_events) {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentListEventsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListEventsBinding.bind(view)
    }

}