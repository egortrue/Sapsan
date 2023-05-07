import groovy.transform.Field
import sapsan.core.Context
import sapsan.utils.Logging

def call(String type = "build") {
    def c = new Context()
    c.script = this
    node("linux") {
        ansiColor('xterm') {
            c.log("log message")
            c.warning("warning message")
            c.error("error message")
        }
    }
}
