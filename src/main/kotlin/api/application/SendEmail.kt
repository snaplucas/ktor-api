package api.application

import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking(CommonPool) {
    //(1)
    val job = launch(CommonPool) {
        sendEmailSuspending() //(2)
        println("Email sent successfully.")
    }
    job.join() //(9)
    println("Finished")
}

suspend fun sendEmailSuspending(): Boolean {
    val msg = async(CommonPool) {
        //(3)
        delay(500)
        "The message content"
    }
    val recipient = async(CommonPool) {
        getReceiverAddressFromDatabase()  //(5)
    }
    println("Waiting for email data")
    val sendStatus = async(CommonPool) {
        sendEmail(recipient.await(), msg.await()) //(7)
    }
    return sendStatus.await() //(8)
}

suspend fun getReceiverAddressFromDatabase(): String { //(4)
    delay(1000)
    return "coroutine@kotlin.org"
}

suspend fun sendEmail(r: String, msg: String): Boolean { //(6)
    delay(2000)
    println("Sent '$msg' to $r")
    return true
}




