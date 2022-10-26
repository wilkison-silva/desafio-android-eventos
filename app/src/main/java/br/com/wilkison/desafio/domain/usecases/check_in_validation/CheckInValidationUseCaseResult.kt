package br.com.wilkison.desafio.domain.usecases.check_in_validation

sealed class CheckInValidationUseCaseResult {
    object Success : CheckInValidationUseCaseResult()
    object ErrorEmailIsBlank: CheckInValidationUseCaseResult()
    object ErrorEmailMalFormed: CheckInValidationUseCaseResult()
    object NameIsBlank: CheckInValidationUseCaseResult()
}