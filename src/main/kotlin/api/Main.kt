package api

import api.data.SetupDataBase
import api.server.startServer

fun main(args: Array<String>) {
    SetupDataBase()
    startServer()
}