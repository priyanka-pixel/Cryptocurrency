package com.example.cryptocurrency.presentation.coin_list.components

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import com.example.cryptocurrency.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolsViewModelTest {
    private lateinit var coinViewModel: CoinListViewModel

    @Mock
    private lateinit var getcoinsUseCase: GetCoinsUseCase

    private val testDispatcher = UnconfinedTestDispatcher()


    @get:Rule
//tell the application to run the tests INSTANTLY, HIGH PRIORITY
    val rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        MockitoAnnotations.openMocks(this)
        coinViewModel = CoinListViewModel(getcoinsUseCase)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `confirm first state is loading`() {
        runBlocking {
            whenever(getcoinsUseCase.invoke()).thenReturn(flow {
                emit(Resource.Loading())
                emit(Resource.Success(listOf()))
            })
            assertEquals(CoinListState(isLoading = false, listOf(), ""), coinViewModel.state.value)
        }
    }

}