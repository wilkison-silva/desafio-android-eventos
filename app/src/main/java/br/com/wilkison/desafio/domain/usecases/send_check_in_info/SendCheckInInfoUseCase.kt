package br.com.wilkison.desafio.domain.usecases.send_check_in_info

interface SendCheckInInfoUseCase {

    suspend fun sendCheckInInfo(
        eventId: String,
        name: String,
        email: String
    ): SendCheckInInfoUseCaseResult
}