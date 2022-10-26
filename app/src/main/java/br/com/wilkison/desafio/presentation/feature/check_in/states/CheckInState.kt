package br.com.wilkison.desafio.presentation.feature.check_in.states

sealed class CheckInState {
    object Loading : CheckInState()
    object Success : CheckInState()
    object Error : CheckInState()
    object EmptyState : CheckInState()
}