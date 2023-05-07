import groovy.transform.Field
import sapsan.core.Context
import sapsan.utils.Logging

def call(String type = "build") {
    Context.instance.script = this
    node("linux") {
        ansiColor('xterm') {
            Context.warning("warning message")
            Context.error("error message")
        }
    }
}
