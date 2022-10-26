package br.com.wilkison.desafio.presentation.feature.check_in

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.wilkison.desafio.domain.usecases.check_in_validation.CheckInValidationUseCase
import br.com.wilkison.desafio.domain.usecases.check_in_validation.CheckInValidationUseCaseResult
import br.com.wilkison.desafio.domain.usecases.get_event_details.GetEventDetailsUseCaseResult
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCase
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCaseResult
import br.com.wilkison.desafio.domain.usecases.send_check_in_info.SendCheckInInfoUseCase
import br.com.wilkison.desafio.domain.usecases.send_check_in_info.SendCheckInInfoUseCaseResult
import br.com.wilkison.desafio.presentation.feature.check_in.states.CheckInState
import br.com.wilkison.desafio.presentation.feature.check_in.states.CheckInValidationState
import br.com.wilkison.desafio.presentation.feature.event_details.states.EventDetailsState
import br.com.wilkison.desafio.presentation.feature.list_events.ListEventsViewModel
import br.com.wilkison.desafio.presentation.feature.list_events.states.ListEventsState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CheckInViewModelTest {

    @get:Rule
    val coroutineRule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()

    private val mockedCheckInValidationUseCase = mockk<CheckInValidationUseCase>()
    private val mockedSendCheckInInfoUseCase = mockk<SendCheckInInfoUseCase>()

    private val viewModel by lazy {
        CheckInViewModel(mockedCheckInValidationUseCase, mockedSendCheckInInfoUseCase)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tears() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN try to do check-in with empty name, THEN response is an instance of type NameIsBlank`() {

        every {
            mockedCheckInValidationUseCase.validate(name = fakeEmptyName, email = fakeEmail)
        }.returns(CheckInValidationUseCaseResult.NameIsBlank)

        viewModel.validateFields(name = fakeEmptyName, email = fakeEmail)

        Assert.assertTrue(viewModel.validationState.value is CheckInValidationState.NameIsBlank)

    }

    @Test
    fun `WHEN try to do check-in with empty email, THEN response is an instance of type EmailIsBlank`() {

        every {
            mockedCheckInValidationUseCase.validate(name = fakeName, email = fakeEmptyEmail)
        }.returns(CheckInValidationUseCaseResult.ErrorEmailIsBlank)

        viewModel.validateFields(name = fakeName, email = fakeEmptyEmail)

        Assert.assertTrue(viewModel.validationState.value is CheckInValidationState.EmailIsBlank)

    }

    @Test
    fun `WHEN try to do check-in with email malformed, THEN response is an instance of type EmailMalFormed`() {

        every {
            mockedCheckInValidationUseCase.validate(name = fakeName, email = fakeEmailMalFormed)
        }.returns(CheckInValidationUseCaseResult.ErrorEmailMalFormed)

        viewModel.validateFields(name = fakeName, email = fakeEmailMalFormed)

        Assert.assertTrue(viewModel.validationState.value is CheckInValidationState.EmailMalFormed)

    }

    @Test
    fun `WHEN try to do check-in with correct name and correct email , THEN response is an instance of type Success`() {

        every {
            mockedCheckInValidationUseCase.validate(name = fakeName, email = fakeEmail)
        }.returns(CheckInValidationUseCaseResult.Success)

        viewModel.validateFields(name = fakeName, email = fakeEmail)

        Assert.assertTrue(viewModel.validationState.value is CheckInValidationState.Success)

    }

    @Test
    fun `WHEN get send check-in infos, THEN a Success response is delivered`() =
        runTest {

            coEvery {
                mockedSendCheckInInfoUseCase.sendCheckInInfo(
                    eventId = fakeEventId,
                    name = fakeName,
                    email = fakeEmail
                )
            }.returns(SendCheckInInfoUseCaseResult.Success)

            viewModel.sendCheckInInfo(
                eventId = fakeEventId,
                name = fakeName,
                email = fakeEmail
            )

            Assert.assertTrue(viewModel.checkInState.value is CheckInState.Success)

        }

    @Test
    fun `WHEN get send check-in infos, THEN an Error response is delivered`() =
        runTest {

            coEvery {
                mockedSendCheckInInfoUseCase.sendCheckInInfo(
                    eventId = fakeEventId,
                    name = fakeName,
                    email = fakeEmail
                )
            }.returns(SendCheckInInfoUseCaseResult.Error)

            viewModel.sendCheckInInfo(
                eventId = fakeEventId,
                name = fakeName,
                email = fakeEmail
            )

            Assert.assertTrue(viewModel.checkInState.value is CheckInState.Error)

        }

    private val fakeEventId = "123"
    private val fakeEmptyName = ""
    private val fakeEmptyEmail = ""
    private val fakeName = "Teste"
    private val fakeEmail = "Teste@yxz.com"
    private val fakeEmailMalFormed = "a vida é bela"
}
