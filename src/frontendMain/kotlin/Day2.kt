import react.*
import react.dom.div

external interface Day2Props : RProps {
}

val day2 = functionalComponent<Day2Props>("Welcome") { props ->
    val input = ""
    div {
        +"Day2"
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
