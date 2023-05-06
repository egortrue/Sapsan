import groovy.transform.Field
import sapsan.core.Context
import sapsan.utils.Logging

@Delegate @Field Context c

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
                    c.log "run.groovy \"type\" parameter is invalid"
            }
        }
    }
}