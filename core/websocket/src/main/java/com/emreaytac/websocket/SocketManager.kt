package com.emreaytac.websocket

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface SocketManager {
    fun connect(url: String)
    fun disconnect()
    fun sendMessage(message: String, onException: (Exception) -> Unit)
    fun observeMessages(): SharedFlow<String>
    fun observeStatus(): StateFlow<SocketStatus>
}