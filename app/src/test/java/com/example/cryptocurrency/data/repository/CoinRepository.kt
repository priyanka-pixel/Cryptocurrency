package com.example.cryptocurrency.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class CoinRepository {
    private lateinit var coinRepository: CoinRepoImpl
    //  private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var Api: CoinPaprikaApi


    @get:Rule
//tell the application to run the tests INSTANTLY, HIGH PRIORITY
    val rule: TestRule = InstantTaskExecutorRule()


    //@OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        // Dispatchers.setMain(dispatcher = testDispatcher)
        MockitoAnnotations.openMocks(this)
        coinRepository = CoinRepoImpl(Api)
    }

    //  @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun teardown() {
        // Dispatchers.resetMain()
    }

    @Test

    fun `simulate Internet Connection Success Status`() {
        runBlocking {
            whenever(Api.getCoins()).thenReturn(listOf(CoinDto("", true, false, "")))

            whenever(Api.getCoinById("")).thenReturn(CoinDetailDto("", "", "", false,"","",true))

            val response1 = coinRepository.getCoins()
            assertEquals(listOf(CoinDto("", true, false, "")), response1)

            //Now, let's test the second API
            val response2 = coinRepository.getCoinById("")
            assertEquals(true, response2.is_active)
        }
    }
}



