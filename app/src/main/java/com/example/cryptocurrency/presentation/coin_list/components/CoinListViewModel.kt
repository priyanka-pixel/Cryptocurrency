package com.example.cryptocurrency.presentation.coin_list.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.domain.use_case.get_coins.getCoinsusecase
import com.tes.android.projects.tvshowsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsusecase: getCoinsusecase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state


    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsusecase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())

                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "error")

                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }
}
