package sapsan.utils

class Colors {

    static String reset = "\\e[0m"

    static String red(String message) {
        return "\\e[31m$message$reset"
    }

}
