package com.example.cryptocurrency.get_Coin_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import com.example.cryptocurrency.presentation.coin_detail.components.CoinDetailState
import com.example.cryptocurrency.presentation.coin_detail.components.CoinDetailViewModel
import com.example.cryptocurrency.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CoinDetailsViewModelTest {
    private lateinit var coinViewModel: CoinDetailViewModel

    @Mock
    private lateinit var getcoinUseCase: GetCoinUseCase
    private val testDispatcher = UnconfinedTestDispatcher()


    @get:Rule
//tell the application to run the tests INSTANTLY, HIGH PRIORITY
    val rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        coinViewModel = CoinDetailViewModel(getcoinUseCase, SavedStateHandle())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `confirm first state is loading`() {
        runBlocking {
            whenever(getcoinUseCase.invoke("1")).thenReturn(flow {
                emit(Resource.Loading())
            })
            assertEquals(CoinDetailState(), coinViewModel.state.value)
        }
    }
}

