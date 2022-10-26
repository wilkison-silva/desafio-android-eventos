package br.com.wilkison.desafio.domain.usecases.check_in_validation

import br.com.wilkison.desafio.extensions.isValidEmail

class CheckInValidationUseCaseImpl(

) : CheckInValidationUseCase {

    override fun validate(name: String, email: String): CheckInValidationUseCaseResult {
        if (email.isBlank())
            return CheckInValidationUseCaseResult.ErrorEmailIsBlank
        if (!email.isValidEmail())
            return CheckInValidationUseCaseResult.ErrorEmailMalFormed
        if (name.isBlank())
            return CheckInValidationUseCaseResult.NameIsBlank
        return CheckInValidationUseCaseResult.Success
    }
}