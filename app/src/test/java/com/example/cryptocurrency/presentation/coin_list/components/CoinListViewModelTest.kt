package com.example.cryptocurrency.presentation.coin_list.components

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.data.repository.CoinRepoImpl
import com.example.cryptocurrency.domain.repository.CoinRepository
import com.example.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolsViewModelTest {
    private var coinViewModel: CoinListViewModel? = null
    private var coinRepository: CoinRepository? = null
    private var getcoinUseCase: GetCoinsUseCase? = null


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
        getcoinUseCase = GetCoinsUseCase(coinRepository!!)

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
            coinViewModel = CoinListViewModel(getcoinUseCase!!)
            val state = coinViewModel!!.state.value
            Assert.assertTrue(state.isLoading)
        }
    }

    @Test
    fun `get Error Response from Api Expect Error State`() {
        runBlocking {
            coinViewModel = CoinListViewModel((getcoinUseCase!!))
            val state = coinViewModel!!.state.value
            Assert.assertEquals("", state.error)
        }
    }
}