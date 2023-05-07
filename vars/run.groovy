import sapsan.util.Log
import sapsan.core.Context

def call(String type = "build") {
    Context.script = this
    Log.script = this
    node("linux") {
        ansiColor('xterm') {
            Context.log.info "log message"
            Context.warning("warning message")
            Context.error("error message")
        }
    }
}
