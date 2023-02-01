package com.example.cryptocurrency.get_Coin_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.data.repository.CoinRepoImpl
import com.example.cryptocurrency.domain.repository.CoinRepository
import com.example.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import com.example.cryptocurrency.presentation.coin_detail.components.CoinDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CoinDetailsViewModelTest {
    private var coinViewModel: CoinDetailViewModel? = null
    private var coinRepository: CoinRepository? = null
    private var getcoinUseCase: GetCoinUseCase? = null


    @Mock
    private lateinit var Api: CoinPaprikaApi
    private val testDispatcher = UnconfinedTestDispatcher()


    @get:Rule
//tell the application to run the tests INSTANTLY, HIGH PRIORITY
    val rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        coinRepository = CoinRepoImpl(Api)
        getcoinUseCase = GetCoinUseCase(coinRepository!!)

    }

    @After
    fun tearDown() {
        coinRepository = null
        getcoinUseCase = null
        coinViewModel = null

        Dispatchers.resetMain()
    }

    @Test
    fun `confirm first state is loading`() {
        runBlocking {
            coinViewModel = CoinDetailViewModel(
                getcoinUseCase!!, savedStateHandle = SavedStateHandle(
                    mapOf("id" to "gg")
                )
            )
            val state = coinViewModel!!.state.value
            assertTrue(state.isLoading)
        }
    }
}

