import kotlinx.browser.window
import react.*
import react.dom.div

external interface Day1Props : RProps {
}

val day1 = functionalComponent<Day1Props>("Welcome") { props ->
    val (input, setInput) = useState("")

    window.fetch("/static/day1.txt")
        .then {
            it.text()
        }.then {
            setInput(it)
        }

    val lines = if (input.isNotEmpty()) input.split("\n").map { it.toInt() } else emptyList()

    val increasedCount = lines.zipWithNext { a: Int, b: Int -> if (a < b) 1 else null }.filterNotNull().size

    div {
        +"Day1"
    }
    div {
        +"Answer: $increasedCount"
    }
//    div {
//        styledDiv {
//            css {
//                +WelcomeStyles.textContainer
//            }
//            +"Hello, $name"
//        }
//        styledInput {
//            css {
//                +WelcomeStyles.textInput
//            }
//            attrs {
//                type = InputType.text
//                value = name
//                onChangeFunction = { event ->
//                    setName((event.target as HTMLInputElement).value)
//                }
//            }
//        }
//    }
}
