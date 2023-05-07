import sapsan.util.Log
import sapsan.core.Context
import sapsan.jenkins.Pipeline

def call() {
    Context.script = this
    Pipeline.run {
        Log.info("log message")
        Log.warning("warning message")
        Log.error("error message")
    }
}
