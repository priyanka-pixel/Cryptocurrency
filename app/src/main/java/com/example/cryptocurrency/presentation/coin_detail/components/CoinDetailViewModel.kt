package com.example.cryptocurrency.presentation.coin_detail.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import com.example.cryptocurrency.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoin_UseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state


    init {
        savedStateHandle.get<String>(CoinPaprikaApi.COIN_ID)?.let { id ->
            getCoin(id)
        }
    }

    private fun getCoin(id: String) {
        getCoin_UseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)

                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "error")

                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }
}
