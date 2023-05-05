
def call(Closure script) {
    node("linux") {
        script()
    }
}