package com.dave_devs.stockmarketapp.feature_stock.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dave_devs.stockmarketapp.feature_stock.domain.util.Routes
import com.dave_devs.stockmarketapp.feature_stock.presentation.company_info.CompanyInfoScreen
import com.dave_devs.stockmarketapp.feature_stock.presentation.company_listings.CompanyListingsScreen

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.COMPANY_LISTINGS_SCREEN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.COMPANY_LISTINGS_SCREEN) {
            CompanyListingsScreen(navController = navController)
        }
        composable(Routes.COMPANY_INFO_SCREEN) {
            CompanyInfoScreen(navController = navController)
        }
    }
}