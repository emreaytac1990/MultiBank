package com.emreaytac.data

import app.cash.turbine.test
import com.emreaytac.data.repository.StockRepositoryImpl
import com.emreaytac.websocket.SocketManager
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StockRepositoryImplTest {

    private lateinit var repository: StockRepositoryImpl
    private val socketManager: SocketManager = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        repository = StockRepositoryImpl(socketManager, testDispatcher)
    }

    @Test
    fun when_ws_messages_received_stockPrices_should_be_updated_and_sorted() = runTest {
        val messages = MutableSharedFlow<String>(replay = 1)

        every { socketManager.observeMessages() } returns messages

        val repository = StockRepositoryImpl(socketManager, UnconfinedTestDispatcher())

        val mockJson = """
        [
            {"id":"1","symbol":"AAPL","price":"150.00","symbolName":"Apple","symbolDescription":"D","timestamp":100},
            {"id":"2","symbol":"TSLA","price":"200.00","symbolName":"Tesla","symbolDescription":"D","timestamp":100}
        ]
        """.trimIndent()


        repository.stockPrices.test {
            // Emit empty initial data
            val firstItem = awaitItem()


            messages.emit(mockJson)

            // Get New data
            val updatedPrices = awaitItem()

            assertEquals(2, updatedPrices.size)
            assertEquals("TSLA", updatedPrices[0].symbol)

            cancelAndIgnoreRemainingEvents()
        }
    }
}