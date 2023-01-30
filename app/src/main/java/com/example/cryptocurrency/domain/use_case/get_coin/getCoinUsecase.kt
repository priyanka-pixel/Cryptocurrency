package com.example.cryptocurrency.domain.use_case.get_coin

import com.example.cryptocurrency.data.remote.dto.toCoinDetail
import com.example.cryptocurrency.domain.module.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
import com.tes.android.projects.tvshowsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class getCoinUsecase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(id: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(id).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occur"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }

    }
}
