package br.com.wilkison.desafio.extensions

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.wilkison.desafio.R
import br.com.wilkison.desafio.databinding.DialogCheckInBinding
import br.com.wilkison.desafio.presentation.feature.check_in.CheckInViewModel
import br.com.wilkison.desafio.presentation.feature.check_in.states.CheckInValidationState
import org.koin.androidx.viewmodel.ext.android.viewModel

fun Fragment.checkInDialog(
    context: Context,
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
) {
    val binding: DialogCheckInBinding = DialogCheckInBinding.inflate(layoutInflater)


    AlertDialog.Builder(context)
        .setView(binding.root)
        .show().apply {

            val viewModel: CheckInViewModel by viewModel()

            binding.buttonCheckIn.setOnClickListener {
                val name = binding.inputName.text.toString()
                val email = binding.inputEmail.text.toString()
                viewModel.doCheckIn(name = name, email = email)
            }
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
                        is CheckInValidationState.EmptyState -> { }
                        is CheckInValidationState.NameIsBlank -> {
                            binding.tiName.error = getString(R.string.nome_nao_pode_ser_vazio)
                            binding.tiName.withError()
                            binding.tiEmail.withoutError()
                        }
                        is CheckInValidationState.Success -> {
                            dismiss()
                        }
                    }
                }
            }
        }
}