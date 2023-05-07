package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS

final class Colors {

    private static String reset = "\033[0m"
    private static String redBg = "\033[101m"
    private static String yellowBg = "\033[43m"

    @NonCPS
    static String red(String message) {
        "$redBg$message$reset"
    }

    @NonCPS
    static String yellow(String message) {
        "$yellowBg$message$reset"
    }

}
