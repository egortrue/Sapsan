@Library("sapsan@master") _
import sapsan.core.Pipeline
import sapsan.util.Log

Pipeline.run(this) {
    Log.info "download artifact..."
    Log.info "deploy artifact..."
}