package sapsan.utils

final class Colors {

    private static String reset = "\033[0m"
    private static String redBg = "\033[101m"
    private static String yellowBg = "\033[43m"
    private static String greenBg = "\033[42m"

    static String red(String message) {
        "$redBg$message$reset"
    }

    static String yellow(String message) {
        "$yellowBg$message$reset"
    }

    static String green(String message) {
        "$greenBg$message$reset"
    }

}
