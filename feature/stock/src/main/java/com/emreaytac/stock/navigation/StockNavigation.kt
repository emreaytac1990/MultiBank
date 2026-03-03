package com.emreaytac.stock.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import androidx.navigation.navigation
import com.emreaytac.navigation.StockDetail
import com.emreaytac.navigation.StockGraph
import com.emreaytac.navigation.StockList
import com.emreaytac.stock.ui.FeedScreen
import com.emreaytac.stock.ui.StockDetailScreen

fun NavGraphBuilder.stockGraph(navController: NavController) {

    navigation<StockGraph>(startDestination = StockList) {


        composable<StockList> {
            FeedScreen(onStockClick = { symbol ->
                navController.navigate(StockDetail(symbol = symbol)) }
            )
        }


        composable<StockDetail>(
            deepLinks = listOf(
                navDeepLink<StockDetail>(basePath = "stocks://symbol")
            )
        ) { backStackEntry ->
            val detail: StockDetail = backStackEntry.toRoute()
            StockDetailScreen(
                symbol = detail.symbol,
                onBack = { navController.popBackStack() }
            )
        }
    }
}