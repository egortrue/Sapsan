package sapsan.util

import com.cloudbees.groovy.cps.NonCPS

/**
 * Статический класс для поддержки цветного вывода.
 * Требует расширения https://plugins.jenkins.io/ansicolor/
 */
final class Color {
    private static String reset = "\033[0m"
    private static String bold = "\033[1m"
    private static String redBg = "\033[101m"
    private static String redText = "\033[31m"
    private static String yellowBg = "\033[43m"
    private static String greenText = "\033[32m"
    private static String cyanText = "\033[36m"

    @NonCPS
    static String red(String message) {
        "$bold$redText$message$reset"
    }

    @NonCPS
    static String yellow(String message) {
        "$yellowBg$message$reset"
    }

    @NonCPS
    static String green(String message) {
        "$bold$greenText$message$reset"
    }

    @NonCPS
    static String cyan(String message) {
        "$bold$cyanText$message$reset"
    }
}
