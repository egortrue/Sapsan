package sapsan.util

import com.cloudbees.groovy.cps.NonCPS

/**
 * Статический класс для поддержки цветного вывода.
 */
final class Color {
    private static String reset = "\033[0m"
    private static String redBg = "\033[101m"
    private static String yellowBg = "\033[43m"
    private static String greenText = "\033[32m"

    @NonCPS
    static String red(String message) {
        "$redBg$message$reset"
    }

    @NonCPS
    static String yellow(String message) {
        "$yellowBg$message$reset"
    }

    @NonCPS
    static String green(String message) {
        "$greenText$message$reset"
    }
}
