package com.yyusufsefa.videogameappcompose.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetAllVideoGameUseCase
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var homeViewModel: HomeViewModel

    @MockK
    private lateinit var getAllVideoGameUseCase: GetAllVideoGameUseCase

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
        homeViewModel = HomeViewModel(getAllVideoGameUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `test GetVideoGames loading state`() = runTest {
        val page = 1
        val pageSize = 20

        coEvery { getAllVideoGameUseCase(page, pageSize) } returns flow {
            emit(Resource.Loading)
        }

        homeViewModel.onEvent(HomeEvent.GetVideoGames(page, pageSize))

        homeViewModel.homeState.test {
            assert(awaitItem().isLoading)
        }
    }

    @Test
    fun `given page and page size when GetAllVideoGameUseCase invoked, then success called`() =
        runTest {
            val page = 1
            val pageSize = 20

            val mockVideoGameList = List(10) { mockk<VideoGame>() }

            coEvery { getAllVideoGameUseCase(page, pageSize) } returns flow {
                emit(Resource.Success(mockVideoGameList))
            }

            homeViewModel.onEvent(HomeEvent.GetVideoGames(page, pageSize))

            Assert.assertEquals(homeViewModel.homeState.value.videoGames, mockVideoGameList.drop(3))
            Assert.assertEquals(
                homeViewModel.homeState.value.headerVideoGames,
                mockVideoGameList.take(3)
            )
        }

}