package com.dave_devs.stockmarketapp.feature_stock.presentation.company_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dave_devs.stockmarketapp.feature_stock.domain.repository.StockRepository
import com.dave_devs.stockmarketapp.feature_stock.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val stockRepository: StockRepository
): ViewModel() {

    var state by mutableStateOf(CompanyInfoState())

    init {
        getCompanyInfos()
    }

    private fun getCompanyInfos() {
        viewModelScope.launch {
            //We get the company symbol to make API call
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch
            state = state.copy(isLoading = true)
            /*
            If we make two or more network call in the same
            coroutine. We need to make it Async call on
            each network calls independently of the outer coroutine.
            * */
            val companyInfoResult = async{stockRepository.getCompanyInfo(symbol) }
            val intraDayResult = async{stockRepository.getIntraDayInfo(symbol)}

            when(val result = companyInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        company = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }

            when(val result = intraDayResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        stockInfo = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }
        }
    }
}