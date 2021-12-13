import kotlinx.browser.document
import kotlinx.browser.window
import react.child
import react.dom.*
import react.router.dom.*

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            browserRouter {
                div {
                    ul {
                        li {
                            routeLink("/day1") {
                                +"Day1"
                            }
                        }
                        li {
                            routeLink("/day2") {
                                +"Day2"
                            }
                        }
                    }
                }
                switch {
                    route("/day1") {
                        child(day1)
                    }
                    route("/day2") {
                        child(day2)
                    }
                }
            }
        }
    }
}
