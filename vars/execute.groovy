import sapsan.core.Context
import sapsan.jenkins.Pipeline
import sapsan.util.Log

def call() {
    Pipeline.run {
        Log.info("log message")
        Log.warning("warning message")
        Log.error("error message")
    }
}
