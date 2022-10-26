package br.com.wilkison.desafio.presentation.feature.check_in

import androidx.lifecycle.ViewModel
import br.com.wilkison.desafio.domain.usecases.check_in_validation.CheckInValidationUseCase
import br.com.wilkison.desafio.domain.usecases.check_in_validation.CheckInValidationUseCaseResult
import br.com.wilkison.desafio.presentation.feature.check_in.states.CheckInValidationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CheckInViewModel(
    private val checkInValidationUseCase: CheckInValidationUseCase
) : ViewModel() {

    private val _validationState: MutableStateFlow<CheckInValidationState> =
        MutableStateFlow(CheckInValidationState.EmptyState)
    val validationState = _validationState.asStateFlow()

    fun validateFields(name: String, email: String) {
        when (checkInValidationUseCase.validate(name = name, email = email)) {
            is CheckInValidationUseCaseResult.ErrorEmailIsBlank -> {
                _validationState.value = CheckInValidationState.EmailIsBlank
            }
            is CheckInValidationUseCaseResult.ErrorEmailMalFormed -> {
                _validationState.value = CheckInValidationState.EmailMalFormed
            }
            is CheckInValidationUseCaseResult.NameIsBlank -> {
                _validationState.value = CheckInValidationState.NameIsBlank
            }
            is CheckInValidationUseCaseResult.Success -> {
                _validationState.value = CheckInValidationState.Success
            }
        }
    }
}