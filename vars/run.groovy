import sapsan.util.Log
import sapsan.core.Context

def call() {
    Context.script = this
    node("linux") {
        ansiColor('xterm') {
            Log.info("log message")
            Log.warning("warning message")
            Log.error("error message")
        }
    }
}
