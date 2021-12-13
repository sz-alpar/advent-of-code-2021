import kotlinx.browser.window
import react.*
import react.dom.div

external interface Day1Props : RProps {
}

val day1 = functionalComponent<Day1Props>("Welcome") { props ->
    val (input, setInput) = useState("Loading...")

    window.fetch("/static/day1.txt")
        .then {
            it.text()
        }.then {
            setInput(it)
        }

    div {
        +"Day1"
    }
    div {
        +input
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
