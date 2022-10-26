package br.com.wilkison.desafio.presentation.feature.list_events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCase
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCaseResult
import br.com.wilkison.desafio.presentation.feature.list_events.states.ListEventsState
import br.com.wilkison.desafio.presentation.model.ShortEventView
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
class ListEventsViewModelTest {
    @get:Rule
    val coroutineRule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()

    private val mockedGetEventsListUseCase = mockk<GetEventsListUseCase>()
    private val viewModel by lazy {
        ListEventsViewModel(mockedGetEventsListUseCase)
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
    fun `WHEN get list of events, THEN a Success response is delivered`() =
        runTest {

            coEvery {
                mockedGetEventsListUseCase.getEventsList()
            }.returns(GetEventsListUseCaseResult.Success(shortEventViewList = fakeShortEventList))

            viewModel.getEventsList()

            assertTrue(viewModel.listEventsState.value is ListEventsState.Success)

        }

    @Test
    fun `WHEN get list of events, THEN an Error response is delivered`() =
        runTest {

            coEvery {
                mockedGetEventsListUseCase.getEventsList()
            }.returns(GetEventsListUseCaseResult.Error)

            viewModel.getEventsList()

            assertTrue(viewModel.listEventsState.value is ListEventsState.Error)

        }

    private val fakeShortEventList = listOf(
        ShortEventView(
            id = "123",
            date = "11/11/11 00:00",
            price = "XYZ",
            title = "TITLE OF EXAMPLE"
        )
    )
}