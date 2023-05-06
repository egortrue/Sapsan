import groovy.transform.Field
import sapsan.core.Context
import sapsan.utils.Logging

@Delegate @Field Context context

def call(String type = "build") {
    context.script = this
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
                    error("run.groovy \"type\" parameter is invalid")
            }
        }
    }
}