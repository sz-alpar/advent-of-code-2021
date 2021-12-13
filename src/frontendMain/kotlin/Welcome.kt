import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.attrs
import react.dom.div
import styled.css
import styled.styledDiv
import styled.styledInput

external interface WelcomeProps : RProps {
    var name: String
}

val welcome = functionalComponent<WelcomeProps>("Welcome") { props ->
    val (name, setName) = useState(props.name)
    div {
        styledDiv {
            css {
                +WelcomeStyles.textContainer
            }
            +"Hello, $name"
        }
        styledInput {
            css {
                +WelcomeStyles.textInput
            }
            attrs {
                type = InputType.text
                value = name
                onChangeFunction = { event ->
                    setName((event.target as HTMLInputElement).value)
                }
            }
        }
    }
}
