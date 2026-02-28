package com.emreaytac.websocket.internal

import com.emreaytac.websocket.SocketManager
import com.emreaytac.websocket.SocketStatus
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

internal class SocketManagerImpl @Inject constructor(private val client: HttpClient) : SocketManager {

    private val _messages = MutableSharedFlow<String>()
    private val _status = MutableStateFlow(SocketStatus.DISCONNECTED)
    private var session : DefaultWebSocketSession? = null
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var connectionJob: Job? = null

    override fun connect(url: String) {
        if (_status.value == SocketStatus.CONNECTED || _status.value == SocketStatus.LOADING) return

        connectionJob?.cancel()
        connectionJob = scope.launch {
            while (isActive){
                try {
                    _status.value = SocketStatus.LOADING

                    client.webSocket(url){
                        session = this
                        _status.value = SocketStatus.CONNECTED

                        incoming.consumeAsFlow().collect{ frame ->
                            if (frame is Frame.Text){
                                _messages.emit(frame.readText())
                            }
                        }
                    }

                } catch (e: Exception){
                    // CancellationException finishes the coroutine
                    if (e is CancellationException) throw e

                    if (isActive) {
                        _status.value = SocketStatus.ERROR
                    }
                } finally {
                    session = null

                    if (isActive) {
                        delay(5000)
                    } else {
                        _status.value = SocketStatus.DISCONNECTED
                    }
                }
            }
        }
    }

    override fun disconnect() {
        connectionJob?.cancel()
        connectionJob = null

        _status.value = SocketStatus.DISCONNECTED

        scope.launch {
            try {
                session?.close()
            } catch (e: Exception) { }
            finally {
                session = null
            }
        }
    }

    override fun sendMessage(
        message: String,
        onException: (Exception) -> Unit
    ) {
        try {
            scope.launch { session?.send(Frame.Text(message)) }
        } catch (e: Exception) {
            onException.invoke(e)
        }
    }

    override fun observeMessages(): SharedFlow<String> = _messages.asSharedFlow()

    override fun observeStatus(): StateFlow<SocketStatus> = _status.asStateFlow()

}
