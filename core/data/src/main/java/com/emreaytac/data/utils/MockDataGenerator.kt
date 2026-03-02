package com.emreaytac.data.utils

import com.emreaytac.data.model.StockPriceResponse
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

private val symbols = listOf(
    "AAPL", "GOOG", "TSLA", "AMZN", "MSFT", "NVDA", "META", "NFLX",
    "AMD", "INTC", "IBM", "ORCL", "CSCO", "ADBE", "CRM", "PYPL",
    "UBER", "LYFT", "SNAP", "SPOT", "SHOP", "SQ", "DOCU", "ZM", "ROKU"
)

private val symbolDescriptions = mapOf(
    "AAPL" to "Apple Inc. focuses on consumer electronics, software, and online services, known for the iPhone and Mac.",
    "GOOG" to "Alphabet Inc. is a multinational conglomerate, best known as the parent company of Google and YouTube.",
    "TSLA" to "Tesla, Inc. designs and manufactures electric vehicles, battery energy storage, and solar products.",
    "AMZN" to "Amazon.com, Inc. is an e-commerce and cloud computing giant, providing services like AWS and Prime Video.",
    "MSFT" to "Microsoft Corporation develops, licenses, and supports software, services, devices, and solutions.",
    "NVDA" to "NVIDIA Corporation is a leader in graphics processing units (GPUs) and AI computing technology.",
    "META" to "Meta Platforms, Inc. builds technologies that help people connect, find communities, and grow businesses.",
    "NFLX" to "Netflix, Inc. is a leading entertainment services provider with millions of paid memberships globally.",
    "AMD" to "Advanced Micro Devices, Inc. produces semiconductor processors and related technologies for business and consumer markets.",
    "INTC" to "Intel Corporation is one of the world's largest semiconductor chip manufacturers and developers.",
    "IBM" to "International Business Machines Corporation provides infrastructure, software, and consulting services.",
    "ORCL" to "Oracle Corporation offers products and services that address enterprise information technology environments.",
    "CSCO" to "Cisco Systems, Inc. designs and sells a broad range of technologies, powered by networking and the internet.",
    "ADBE" to "Adobe Inc. is a software company specializing in creative, marketing, and document management solutions.",
    "CRM" to "Salesforce, Inc. provides customer relationship management (CRM) software and applications focused on sales.",
    "PYPL" to "PayPal Holdings, Inc. operates a worldwide online payments system that supports online money transfers.",
    "UBER" to "Uber Technologies, Inc. provides ride-hailing, food delivery, and freight transport services.",
    "LYFT" to "Lyft, Inc. operates a marketplace for on-demand ridesharing in the United States and Canada.",
    "SNAP" to "Snap Inc. is a camera company, known for its popular social media app, Snapchat.",
    "SPOT" to "Spotify Technology S.A. is a leading audio streaming service, offering music and podcasts.",
    "SHOP" to "Shopify Inc. provides an e-commerce platform for online stores and retail point-of-sale systems.",
    "SQ" to "Block, Inc. (Square) develops tools that empower businesses and individuals to participate in the economy.",
    "DOCU" to "DocuSign, Inc. provides electronic signature technology and Digital Transaction Management services.",
    "ZM" to "Zoom Video Communications, Inc. offers a communications platform that connects people through video and voice.",
    "ROKU" to "Roku, Inc. operates a TV streaming platform, providing access to movies, shows, and live TV."
)

internal fun generateMockList(): List<StockPriceResponse> {
    return symbols.map {
        generateMockPriceResponse(it)
    }
}

private fun generateMockPriceResponse(symbol: String): StockPriceResponse {
    val (symbolName, baseID, basePrice) = when (symbol) {
        "AAPL" -> Triple("Apple Inc.", "32134", BigDecimal("264.18"))
        "GOOG" -> Triple("Alphabet Inc.", "32135", BigDecimal("311.43"))
        "TSLA" -> Triple("Tesla, Inc.", "32136", BigDecimal("402.51"))
        "AMZN" -> Triple("Amazon.com, Inc.", "32137", BigDecimal("215.10"))
        "MSFT" -> Triple("Microsoft Corp.", "32138", BigDecimal("490.45"))
        "NVDA" -> Triple("NVIDIA Corp.", "32139", BigDecimal("145.20"))
        "META" -> Triple("Meta Platforms", "32140", BigDecimal("585.30"))
        "NFLX" -> Triple("Netflix, Inc.", "32141", BigDecimal("820.15"))
        "AMD"  -> Triple("Advanced Micro Devices", "32142", BigDecimal("165.40"))
        "INTC" -> Triple("Intel Corp.", "32143", BigDecimal("24.80"))
        "IBM"  -> Triple("IBM Corp.", "32144", BigDecimal("235.60"))
        "ORCL" -> Triple("Oracle Corp.", "32145", BigDecimal("175.25"))
        "CSCO" -> Triple("Cisco Systems", "32146", BigDecimal("52.10"))
        "ADBE" -> Triple("Adobe Inc.", "32147", BigDecimal("540.80"))
        "CRM"  -> Triple("Salesforce, Inc.", "32148", BigDecimal("295.50"))
        "PYPL" -> Triple("PayPal Holdings", "32149", BigDecimal("82.40"))
        "UBER" -> Triple("Uber Technologies", "32150", BigDecimal("78.90"))
        "LYFT" -> Triple("Lyft, Inc.", "32151", BigDecimal("16.30"))
        "SNAP" -> Triple("Snap Inc.", "32152", BigDecimal("12.15"))
        "SPOT" -> Triple("Spotify Technology", "32153", BigDecimal("420.75"))
        "SHOP" -> Triple("Shopify Inc.", "32154", BigDecimal("95.20"))
        "SQ"   -> Triple("Block, Inc.", "32155", BigDecimal("88.40"))
        "DOCU" -> Triple("DocuSign, Inc.", "32156", BigDecimal("65.10"))
        "ZM"   -> Triple("Zoom Video", "32157", BigDecimal("72.30"))
        "ROKU" -> Triple("Roku, Inc.", "32158", BigDecimal("68.90"))
        else   -> Triple("Unknown Stock", "00000", BigDecimal("100.00"))
    }

    val randomValue = Random.nextDouble(-5.0, 5.0).toString()
    val randomChange = BigDecimal(randomValue).setScale(2, RoundingMode.HALF_UP)
    val price = (basePrice + randomChange).setScale(2, RoundingMode.HALF_UP)

    return StockPriceResponse(
        id = baseID,
        symbol = symbol,
        symbolName = symbolName,
        symbolDescription = getDescriptionForSymbol(symbol),
        price = price,
        timestamp = System.currentTimeMillis()
    )
}

private fun getDescriptionForSymbol(symbol: String): String {
    return symbolDescriptions[symbol] ?: "Description not available for this symbol."
}