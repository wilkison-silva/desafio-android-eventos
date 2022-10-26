package br.com.wilkison.desafio.domain.usecases.send_check_in_info

sealed class SendCheckInInfoUseCaseResult {
    object Success : SendCheckInInfoUseCaseResult()
    object Error: SendCheckInInfoUseCaseResult()
}