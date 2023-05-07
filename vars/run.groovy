import groovy.transform.Field
import sapsan.utils.Logging
import sapsan.core.Context

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
