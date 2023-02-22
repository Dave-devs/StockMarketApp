package com.dave_devs.stockmarketapp.feature_stock.presentation.company_listings

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(start = true)
fun CompanyListingsScreen(
    modifier: Modifier = Modifier,
    viewModel: CompanyListingsViewModel = hiltViewModel()
) {

}