package com.emreaytac.multibank.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.emreaytac.navigation.StockGraph
import com.emreaytac.stock.navigation.stockGraph


@Composable
fun MultiBankNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = StockGraph
    ) {
        stockGraph(navController)
    }
}