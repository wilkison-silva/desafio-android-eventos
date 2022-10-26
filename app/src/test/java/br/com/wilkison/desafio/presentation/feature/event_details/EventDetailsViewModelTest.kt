package br.com.wilkison.desafio.presentation.feature.event_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.wilkison.desafio.domain.usecases.get_event_details.GetEventDetailsUseCase
import br.com.wilkison.desafio.domain.usecases.get_event_details.GetEventDetailsUseCaseResult
import br.com.wilkison.desafio.presentation.feature.event_details.states.EventDetailsState
import br.com.wilkison.desafio.presentation.model.EventView
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class EventDetailsViewModelTest {

    @get:Rule
    val coroutineRule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()

    private val mockedGetEventsDetailsUseCase = mockk<GetEventDetailsUseCase>()
    private val viewModel by lazy {
        EventDetailsViewModel(mockedGetEventsDetailsUseCase)
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
    fun `WHEN get events details, THEN a Success response is delivered`() =
        runTest {

            coEvery {
                mockedGetEventsDetailsUseCase.getEventDetails(fakeEventView.id)
            }.returns(GetEventDetailsUseCaseResult.Success(eventView = fakeEventView))

            viewModel.getEventDetails(eventId = fakeEventView.id)

            assertTrue(viewModel.eventDetailsState.value is EventDetailsState.Success)

        }

    @Test
    fun `WHEN get events details, THEN an Error response is delivered`() =
        runTest {

            coEvery {
                mockedGetEventsDetailsUseCase.getEventDetails(eventId = fakeEventView.id)
            }.returns(GetEventDetailsUseCaseResult.Error)

            viewModel.getEventDetails(eventId = fakeEventView.id)

            assertTrue(viewModel.eventDetailsState.value is EventDetailsState.Error)

        }



    private val fakeEventView = EventView(
        id = "123",
        date = "11/11/11 00:00",
        price = "XYZ",
        title = "TITLE OF EXAMPLE",
        description = "abcdefghijklmnopqrstuv",
        image = "Http",
        longitude = -1.0,
        latitude = -1.0
    )

}