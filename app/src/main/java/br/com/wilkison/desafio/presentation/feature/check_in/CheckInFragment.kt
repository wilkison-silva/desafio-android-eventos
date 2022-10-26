package br.com.wilkison.desafio.presentation.feature.check_in

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.wilkison.desafio.R
import br.com.wilkison.desafio.databinding.FragmentCheckInBinding
import br.com.wilkison.desafio.extensions.withError
import br.com.wilkison.desafio.extensions.withoutError
import br.com.wilkison.desafio.presentation.feature.check_in.states.CheckInValidationState
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInFragment : Fragment(R.layout.fragment_check_in) {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentCheckInBinding
    private val viewModel: CheckInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheckInBinding.bind(view)

        setupButtons()
        prepareObservers()
    }

    private fun prepareObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.validationState.collect {
                when (it) {
                    is CheckInValidationState.EmailIsBlank -> {
                        binding.tiEmail.error = getString(R.string.email_nao_pode_ser_vazio)
                        binding.tiEmail.withError()
                        binding.tiName.withoutError()
                    }
                    is CheckInValidationState.EmailMalFormed -> {
                        binding.tiEmail.error = getString(R.string.email_invalido)
                        binding.tiEmail.withError()
                        binding.tiName.withoutError()
                    }
                    is CheckInValidationState.EmptyState -> {}
                    is CheckInValidationState.NameIsBlank -> {
                        binding.tiName.error = getString(R.string.nome_nao_pode_ser_vazio)
                        binding.tiName.withError()
                        binding.tiEmail.withoutError()
                    }
                    is CheckInValidationState.Success -> {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    private fun setupButtons() {
        binding.buttonBack.setOnClickListener {
            navController.popBackStack()
        }

        binding.buttonCheckIn.setOnClickListener {
            val name = binding.inputName.text.toString()
            val email = binding.inputEmail.text.toString()
            viewModel.validateFields(name = name, email = email)
        }
    }

}