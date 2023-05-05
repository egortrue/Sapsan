import java.util.concurrent.Callable
import java.util.function.Function
import sapsan.core.SapsanException

def call(String type = "build") {
    node("linux") {
        ansiColor("xterm") {
            switch (type) {
                case "build":
                    break
                case "delivery":
                    break
                case "deploy":
                    break
                default:
                    throw new SapsanException("run type is not valid")
            }
        }
    }
}