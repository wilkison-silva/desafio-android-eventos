package br.com.wilkison.desafio.presentation.feature.check_in.states

sealed class CheckInValidationState {
    object NameIsBlank : CheckInValidationState()
    object EmailIsBlank : CheckInValidationState()
    object EmailMalFormed : CheckInValidationState()
    object Success : CheckInValidationState()
    object EmptyState : CheckInValidationState()
}