import groovy.transform.Field
import sapsan.core.Context
import sapsan.utils.Logging

def call(String type = "build") {
    Context.script = this
    Logging.script = this
    node("linux") {
        ansiColor('xterm') {
            Context.log("log message")
            Context.warning("warning message")
            Context.error("error message")
        }
    }
}
