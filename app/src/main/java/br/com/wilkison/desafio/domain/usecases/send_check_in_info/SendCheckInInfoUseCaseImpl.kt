package br.com.wilkison.desafio.domain.usecases.send_check_in_info

import br.com.wilkison.desafio.domain.repository.EventRepository
import br.com.wilkison.desafio.domain.repository.results.SendCheckInInfoResults

class SendCheckInInfoUseCaseImpl(
    private val eventRepository: EventRepository
) : SendCheckInInfoUseCase{

    override suspend fun sendCheckInInfo(
        eventId: String,
        name: String,
        email: String
    ): SendCheckInInfoUseCaseResult {
        val result = eventRepository.sendCheckInInfo(
            eventId = eventId,
            name = name,
            email = email
        )
        return when (result) {
            is SendCheckInInfoResults.Error -> {
                SendCheckInInfoUseCaseResult.Error
            }
            is SendCheckInInfoResults.Success -> {
                SendCheckInInfoUseCaseResult.Success
            }
        }
    }
}