import groovy.transform.Field
import sapsan.core.Context
import sapsan.utils.Logging

def call(String type = "build") {
    Context.script = this
    node("linux") {
        ansiColor('xterm') {

        }
    }
}
