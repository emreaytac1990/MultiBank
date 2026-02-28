package com.emreaytac.stock.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import androidx.navigation.navigation
import com.emreaytac.navigation.StockDetail
import com.emreaytac.navigation.StockGraph
import com.emreaytac.navigation.StockList

fun NavGraphBuilder.stockGraph(navController: NavController) {

    navigation<StockGraph>(startDestination = StockList) {


        composable<StockList> {
            StockListScreen(onItemClick = { id ->
                navController.navigate(StockDetail(itemId = id))
            })
        }


        composable<StockDetail>(
            deepLinks = listOf(
                navDeepLink<StockDetail>(basePath = "https://myapp.com/detail")
            )
        ) { backStackEntry ->
            val detail: StockDetail = backStackEntry.toRoute()
            StockDetailScreen(
                itemId = detail.itemId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}