package com.yyusufsefa.videogameappcompose.domain

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.data.repository.VideoGameRepositoryImpl
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetAllVideoGameUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class GetAllVideoGameDetailUseCaseTest {


    @MockK
    private lateinit var videoGameRepositoryImpl: VideoGameRepositoryImpl

    private lateinit var getAllVideoGameUseCase: GetAllVideoGameUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getAllVideoGameUseCase = GetAllVideoGameUseCase(videoGameRepositoryImpl)
    }

    @Test
    fun `given page and pageSize, when GetAllVideGameUseCase invoked, then loading and success called`() =
        runTest {
            val page = 1
            val pageSize = 20

            val mockVideoGames = mockk<List<VideoGame>>()

            coEvery {
                videoGameRepositoryImpl.getAllVideoGame(
                    page,
                    pageSize
                )
            } returns mockVideoGames

            val result = getAllVideoGameUseCase(page, pageSize)


            result.test {
                Truth.assertThat(awaitItem()).isEqualTo(Resource.Loading)
                Truth.assertThat(awaitItem()).isEqualTo(Resource.Success(mockVideoGames))
                awaitComplete()
            }
        }

}