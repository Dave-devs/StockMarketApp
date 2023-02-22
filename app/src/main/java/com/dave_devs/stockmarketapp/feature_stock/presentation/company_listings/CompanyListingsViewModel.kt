package com.dave_devs.stockmarketapp.feature_stock.presentation.company_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dave_devs.stockmarketapp.feature_stock.domain.repository.StockRepository
import com.dave_devs.stockmarketapp.feature_stock.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val stockRepository: StockRepository
): ViewModel() {

    var state by mutableStateOf(CompanyListingsState())

    private var searchJob: Job? = null

    init {
        getCompanyListings()
    }

    fun onEvent(event: CompanyListingsEvent) {
        when(event) {
            is CompanyListingsEvent.Refresh -> {
                getCompanyListings(
                    fetchFromRemote = true
                )
            }
            is CompanyListingsEvent.OnSearchQueryChange -> {
                state = state.copy( searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListings()
                }
            }
        }
    }

    private fun getCompanyListings(
        fetchFromRemote: Boolean = false,
        query: String = state.searchQuery.lowercase(Locale.ROOT)
    ) {
        viewModelScope.launch {
            stockRepository.getCompanyListings(fetchFromRemote, query).collect{ result ->
                when(result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            state = state.copy(
                                companies = listings
                            )
                        }
                    }
                   is Resource.Error -> Unit
                }
            }
        }
    }
}