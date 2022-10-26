package br.com.wilkison.desafio.presentation.feature.check_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wilkison.desafio.domain.usecases.check_in_validation.CheckInValidationUseCase
import br.com.wilkison.desafio.domain.usecases.check_in_validation.CheckInValidationUseCaseResult
import br.com.wilkison.desafio.domain.usecases.send_check_in_info.SendCheckInInfoUseCase
import br.com.wilkison.desafio.domain.usecases.send_check_in_info.SendCheckInInfoUseCaseResult
import br.com.wilkison.desafio.presentation.feature.check_in.states.CheckInState
import br.com.wilkison.desafio.presentation.feature.check_in.states.CheckInValidationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CheckInViewModel(
    private val checkInValidationUseCase: CheckInValidationUseCase,
    private val sendCheckInInfoUseCase: SendCheckInInfoUseCase
) : ViewModel() {

    private val _validationState: MutableStateFlow<CheckInValidationState> =
        MutableStateFlow(CheckInValidationState.EmptyState)
    val validationState = _validationState.asStateFlow()

    private val _checkInState: MutableStateFlow<CheckInState> =
        MutableStateFlow(CheckInState.EmptyState)
    val checkInState = _checkInState.asStateFlow()

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

    fun sendCheckInInfo(eventId: String, name: String, email: String) {
        viewModelScope.launch {
            _checkInState.value = CheckInState.Loading
            when (sendCheckInInfoUseCase.sendCheckInInfo(
                eventId = eventId,
                name = name,
                email = email
            )) {
                SendCheckInInfoUseCaseResult.Error -> {
                    _checkInState.value = CheckInState.Error
                }
                SendCheckInInfoUseCaseResult.Success -> {
                    _checkInState.value = CheckInState.Success
                }
            }
        }
    }
}