package com.yyusufsefa.videogameappcompose.data.repository

import com.google.common.truth.Truth
import com.yyusufsefa.videogameappcompose.data.local.dao.VideoGameDao
import com.yyusufsefa.videogameappcompose.data.remote.api.VideoGameApi
import com.yyusufsefa.videogameappcompose.data.remote.dto.VideoGameResponse
import com.yyusufsefa.videogameappcompose.data.remote.dto.VideoGameResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class VideoGameRepositoryTest {

    @MockK
    private lateinit var videoGameApi: VideoGameApi

    @MockK
    private lateinit var videoGameDao: VideoGameDao

    private lateinit var videoGameRepositoryImpl: VideoGameRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        videoGameRepositoryImpl = VideoGameRepositoryImpl(videoGameApi, videoGameDao)
    }

    @Test
    fun `given page, pageSize when getVideoGames then videoGameApi called`() = runTest {

        val page = 1
        val pageSize = 10

        val mockResponse = VideoGameResponse(
            count = 10,
            next = "",
            description = "",
            results = listOf(
                VideoGameResult(name = null)
            )
        )

        coEvery { videoGameApi.getVideoGames(page, pageSize) } returns mockResponse

        val result = videoGameRepositoryImpl.getAllVideoGame(page, pageSize)

        coVerify { videoGameApi.getVideoGames(page, pageSize) }

        Truth.assertThat(result.first().name).isEqualTo(null)

    }


}