package br.com.wilkison.desafio.domain.repository.results

sealed class SendCheckInInfoResults {
    object Success : SendCheckInInfoResults()
    object Error : SendCheckInInfoResults()
}