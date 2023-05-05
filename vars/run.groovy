import sapsan.core.Context

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
                    Context.error ""
            }
        }
    }
}