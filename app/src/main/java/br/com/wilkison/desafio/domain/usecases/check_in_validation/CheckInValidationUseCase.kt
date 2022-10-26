package br.com.wilkison.desafio.domain.usecases.check_in_validation

interface CheckInValidationUseCase {

    fun validate(name: String, email: String) : CheckInValidationUseCaseResult

}