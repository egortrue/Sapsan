import sapsan.Context
import sapsan.jenkins.Pipeline
import sapsan.util.Log

def call() {
    Context.script = this
    Pipeline.run {
        Log.info("log message")
        Log.warning("warning message")
        Log.error("error message")
    }
}
