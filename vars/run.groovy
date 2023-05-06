import groovy.transform.Field
import sapsan.core.Context
import sapsan.utils.Logging

def call(String type = "build") {
    Context.script = this
    node("linux") {
        ansiColor('xterm') {
            switch (type) {
                case "build":
                    break
                case "delivery":
                    break
                case "deploy":
                    break
                default:
                    Context.warning("run.groovy \"type\" parameter is invalid")
            }
        }
    }
}