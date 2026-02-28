package com.emreaytac.navigation

import kotlinx.serialization.Serializable


@Serializable object StockGraph

@Serializable object StockList
@Serializable data class StockDetail(val itemId: String)
