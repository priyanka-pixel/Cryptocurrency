package com.example.cryptocurrency.domain.use_case.get_coins

import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.domain.module.Coin
import com.example.cryptocurrency.domain.repository.CoinRepository
import com.example.cryptocurrency.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occur"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }

    }
}