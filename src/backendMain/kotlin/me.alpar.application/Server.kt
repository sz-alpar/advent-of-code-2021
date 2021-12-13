package me.alpar.application

import com.github.lamba92.ktor.features.SinglePageApplication
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.*
import kotlinx.html.*

fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    body {
        div {
            +"Hello from Ktor"
        }
        div {
            id = "root"
        }
        script(src = "/static/advent-of-code-2021.js") {}
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(DefaultHeaders)
        install(CallLogging)
        install(Routing)
        install(SinglePageApplication) {
            defaultPage = "index.html"
            folderPath = "html/"
            ignoreIfContains = """/static/.*""".toRegex()
        }
        routing {
            static("/static") {
                resources("js")
                resources("txt")
            }
        }
    }.start(wait = true)
}